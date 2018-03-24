package com.njfu.ia.process.mapper;

import com.njfu.ia.process.domain.UpstreamDataRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UpstreamDataRecordMapper {

    /**
     * 批量插入上行数据
     *
     * @param upstreamDataRecords upstreamDataRecords
     * @return count
     */
    int batchInsertDataRecords(List<UpstreamDataRecord> upstreamDataRecords);

    /**
     * 查询指定时间间隔内有数据记录的device id
     *
     * @param second    second
     * @param deviceIds deviceIds
     * @return device id list
     */
    List<Integer> selectHasRecordDevice(@Param("second") int second, @Param("deviceIds") List<Integer> deviceIds);
}
