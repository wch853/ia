package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.AlarmRecord;
import com.njfu.ia.sys.mapper.AlarmRecordMapper;
import com.njfu.ia.sys.service.AlarmRecordService;
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
     * @param alarmRecord alarmRecord
     * @param start       start
     * @param end         end
     * @return AlarmRecord list
     */
    @Override
    public List<AlarmRecord> getAlarmRecords(AlarmRecord alarmRecord, String start, String end) {
        return alarmRecordMapper.selectAlarmRecord(alarmRecord, start, end);
    }
}
