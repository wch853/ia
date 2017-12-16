package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.WarnRecord;
import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.enums.WarnRecordFlagEnum;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.mapper.WarnRecordMapper;
import com.njfu.wa.sys.service.WarnRecordService;
import com.njfu.wa.sys.utils.CommonConstants;
import com.njfu.wa.sys.utils.Result;
import com.njfu.wa.sys.websocket.TipHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@EnableScheduling
public class WarnRecordServiceImpl implements WarnRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarnRecordServiceImpl.class);
    @Resource
    private WarnRecordMapper warnRecordMapper;
    @Resource
    private TipHandler tipHandler;

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

            // 查询未处理报警记录数量
            Integer count = this.getUnhandleRecordCount();
            LOGGER.info("warn count: {}", count);

            if (count > 0) {
                tipHandler.broadcastTip(Result.response(ResultEnum.WARN, count));
            }

            // TODO 邮件推送
        } catch (Exception e) {
            LOGGER.error("scanFieldStatus定时任务异常", e);
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
