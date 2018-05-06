package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.AlarmRecord;
import com.njfu.ia.sys.mapper.AlarmRecordMapper;
import com.njfu.ia.sys.service.AlarmRecordService;
import com.njfu.ia.sys.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AlarmRecordServiceImpl implements AlarmRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmRecordServiceImpl.class);

    @Resource
    private AlarmRecordMapper alarmRecordMapper;

    /**
     * 查询报警记录
     *
     * @param alarmRecord
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<AlarmRecord> queryAlarmRecords(AlarmRecord alarmRecord, String start, String end) {
        return alarmRecordMapper.selectAlarmRecord(alarmRecord, start, end);
    }

    /**
     * 查询未处理报警记录
     *
     * @param alarmRecord
     * @return
     */
    @Override
    public List<AlarmRecord> queryUnhandleRecords() {
        AlarmRecord record = new AlarmRecord();
        record.setHandleFlag(Constants.UNHANDLE_FLAG);
        return alarmRecordMapper.selectAlarmRecord(record, null, null);
    }

    /**
     * 查询未处理报警记录数量
     *
     * @return
     */
    @Override
    public int queryUnhandleRecordsCount() {
        int count = 0;
        if (!Constants.USE_SHIRO || (Constants.USE_SHIRO && SecurityUtils.getSubject().isPermitted(Constants.WARN_PERM))) {
            List<AlarmRecord> unhandleRecords = this.queryUnhandleRecords();
            count = unhandleRecords.size();
        }
        return count;
    }

    /**
     * 更新报警记录处理状态位
     *
     * @param ids
     * @param flag
     */
    @Override
    public void modifyAlarmRecordFlag(Integer[] ids, Integer flag) {
        int count = alarmRecordMapper.updateAlarmRecordHandleFlag(ids, flag);
        if (count <= 0) {
            throw new RuntimeException("更新报警记录处理状态位失败！");
        }
    }
}
