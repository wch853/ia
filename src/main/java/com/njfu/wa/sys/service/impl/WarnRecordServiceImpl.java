package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Employee;
import com.njfu.wa.sys.domain.WarnRecord;
import com.njfu.wa.sys.enums.DataTypeEnum;
import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.enums.WarnRecordFlagEnum;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.mail.MailService;
import com.njfu.wa.sys.mapper.EmployeeMapper;
import com.njfu.wa.sys.mapper.WarnRecordMapper;
import com.njfu.wa.sys.service.WarnRecordService;
import com.njfu.wa.sys.utils.CommonConstants;
import com.njfu.wa.sys.utils.Result;
import com.njfu.wa.sys.websocket.TipHandler;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class WarnRecordServiceImpl implements WarnRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarnRecordServiceImpl.class);

    @Resource
    private WarnRecordMapper warnRecordMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private TipHandler tipHandler;

    @Resource
    private MailService mailService;

    /**
     * 扫描大棚状态表，出现异常数据，插入报警记录
     */
    @Override
    @Scheduled(cron = CommonConstants.SCAN_FIELD_STATUS_CRON)
    public void scanFieldStatus() {
        try {
            long start = System.currentTimeMillis();
            // 调用存储过程，扫描大棚状态并生成报警记录
            warnRecordMapper.checkWarn();
            long end = System.currentTimeMillis();
            LOGGER.info("check warn spend: {}", end - start);

            // 查询未处理报警记录
            List<WarnRecord> unHandleWarnRecords = this.getUnHandleWarnRecord();
            if (!CollectionUtils.isEmpty(unHandleWarnRecords)) {
                // 获取报警数量
                Integer count = unHandleWarnRecords.size();
                // websocket推送报警数量
                tipHandler.broadcastTip(Result.response(ResultEnum.WARN, count));
                LOGGER.info("warn count: {}", count);

                if (CommonConstants.USE_WARN_MAIL) {
                    // 邮件推送开关打开，推送报警邮件
                    this.sendWarnMail(unHandleWarnRecords);
                }
            }
        } catch (Exception e) {
            LOGGER.error("scanFieldStatus定时任务异常", e);
        }
    }

    /**
     * 发送报警邮件
     */
    private void sendWarnMail(List<WarnRecord> warnRecords) {
        List<String> toList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setMailStatus(CommonConstants.SEND_WARN_MAIL);
        List<Employee> employees = employeeMapper.selectEmployees(employee);
        if (!CollectionUtils.isEmpty(employees)) {
            for (Employee emp : employees) {
                if (null != emp.getEmpMail()) {
                    // 获取邮件推送地址列表
                    toList.add(emp.getEmpMail());
                }
            }

            if (!CollectionUtils.isEmpty(toList)) {
                // 拼接邮件内容
                StringBuilder sb = new StringBuilder();
                for (WarnRecord warnRecord : warnRecords) {
                    if (warnRecord.getWarnCount() < CommonConstants.SEND_MAIL_WARN_COUNT) {
                        // 低于邮件推送报警次数最低限制，不推送
                        continue;
                    }
                    sb.append("大棚：").append(warnRecord.getFieldId()).append(" ")
                            .append("报警时间：").append(DateFormatUtils.format(warnRecord.getWarnTime(), CommonConstants.DATE_SECOND_FORMAT)).append(" ")
                            .append("报警类型：").append(DataTypeEnum.reflectMap.get(warnRecord.getWarnType())).append(" ")
                            .append("报警值：").append(warnRecord.getWarnVal()).append(" ")
                            .append("报警次数：").append(warnRecord.getWarnCount()).append("\r\n");
                }
                // 群发邮件
                mailService.sendSimpleMail("智慧农业-报警", sb.toString(), toList);
            }
        }
    }

    /**
     * 查询报警记录
     *
     * @param warnRecord fieldId warnType warnTime flag
     * @param start      start
     * @param end        end
     * @return data
     */
    @Override
    public List<WarnRecord> getWarnRecord(WarnRecord warnRecord, String start, String end) {
        return warnRecordMapper.selectWarnRecord(warnRecord, start, end);
    }


    /**
     * 查询未处理报警记录
     *
     * @return data
     */
    @Override
    public List<WarnRecord> getUnHandleWarnRecord() {
        return warnRecordMapper.selectWarnRecordByFlag(WarnRecordFlagEnum.UNHANDLE.code());
    }

    /**
     * 修改报警记录处理标志
     *
     * @param ids  id
     * @param flag flag
     */
    @Override
    public void modifyWarnRecord(Integer[] ids, String flag) {
        int res = warnRecordMapper.updateWarnRecord(ids, flag);
        if (res <= 0) {
            throw new BusinessException("修改报警记录处理标志失败！");
        }
    }

    /**
     * 获取未处理报警记录数量
     *
     * @return count
     */
    @Override
    public int getUnhandleRecordCount() {
        return warnRecordMapper.selectCount(WarnRecordFlagEnum.UNHANDLE.code());
    }

}
