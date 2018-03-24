package com.njfu.ia.process.mapper;

import com.njfu.ia.process.domain.AlarmRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmRecordMapper {

    /**
     * 新增报警数据
     *
     * @param record record
     * @return count
     */
    int insertAlarmRecord(AlarmRecord record);
}
