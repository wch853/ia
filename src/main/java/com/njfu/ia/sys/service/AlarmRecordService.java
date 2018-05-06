package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.AlarmRecord;

import java.util.List;

public interface AlarmRecordService {

    /**
     * 查询报警记录
     *
     * @param alarmRecord
     * @param start
     * @param end
     * @return
     */
    List<AlarmRecord> queryAlarmRecords(AlarmRecord alarmRecord, String start, String end);

    /**
     * 查询未处理报警记录
     *
     * @return
     */
    List<AlarmRecord> queryUnhandleRecords();

    /**
     * 查询未处理报警记录数量
     *
     * @return
     */
    int queryUnhandleRecordsCount();

    /**
     * 更新报警记录处理状态位
     *
     * @param ids
     * @param flag
     */
    void modifyAlarmRecordFlag(Integer[] ids, Integer flag);
}
