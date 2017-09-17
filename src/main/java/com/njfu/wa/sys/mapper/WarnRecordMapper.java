package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.WarnRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WarnRecordMapper {

    /**
     * 插入报警记录
     *
     * @param warnRecord *
     * @return row count
     */
    int insetWarnRecord(WarnRecord warnRecord);

    /**
     * 查询报警记录
     *
     * @param warnRecord fieldId warnType warnTime flag
     * @return data
     */
    List<WarnRecord> selectWarnRecord(@Param("warnRecord") WarnRecord warnRecord,
                                      @Param("start") String start,
                                      @Param("end") String end);

    /**
     * 修改报警记录
     *
     * @param warnRecord id flag
     * @return row count
     */
    int updateWarnRecord(WarnRecord warnRecord);
}
