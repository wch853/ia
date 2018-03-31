package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.AlarmRecord;

import java.util.List;

public interface AlarmRecordService {

    /**
     * 查询报警记录
     *
     * @param alarmRecord alarmRecord
     * @param start       start
     * @param end         end
     * @return AlarmRecord list
     */
    List<AlarmRecord> getAlarmRecords(AlarmRecord alarmRecord, String start, String end);
}
