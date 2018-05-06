package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.AlarmRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlarmRecordMapper {

    /**
     * 查询报警记录
     *
     * @param alarmRecord alarmRecord
     * @return
     */
    List<AlarmRecord> selectAlarmRecord(@Param("alarmRecord") AlarmRecord alarmRecord,
                                       @Param("start") String start,
                                       @Param("end") String end);

    /**
     * 修改报警记录处理状态
     *
     * @return
     */
    int updateAlarmRecordHandleFlag(@Param("ids") Integer[] ids, @Param("flag") Integer flag);

    /**
     * 获取报警记录数量
     *
     * @param
     * @return
     */
    int selectCount(@Param("flag") String flag);

}
