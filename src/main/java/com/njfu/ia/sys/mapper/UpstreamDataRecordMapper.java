package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.UpstreamDataRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UpstreamDataRecordMapper {

    /**
     * 查询上传数据记录
     *
     * @param record
     * @param start
     * @param end
     * @return
     */
    List<UpstreamDataRecord> selectDataRecords(@Param("record") UpstreamDataRecord record,
                                               @Param("start") String start, @Param("end") String end);

    /**
     * 根据区块编号查询近期有数据上传记录的数据类型及时间
     *
     * @param sectionId
     * @param minutes
     * @return
     */
    List<UpstreamDataRecord> selectLastReceiveTime(@Param("sectionId") Integer sectionId,
                                                   @Param("minutes") long minutes);

    /**
     * 查询近期数据上传记录
     *
     * @param sectionId
     * @param dataType
     * @param receiveTime
     * @param useStatus
     * @return
     */
    List<UpstreamDataRecord> selectLastDataRecords(@Param("sectionId") Integer sectionId,
                                                   @Param("dataType") Integer dataType,
                                                   @Param("receiveTime") Date receiveTime,
                                                   @Param("useStatus") Integer useStatus);
}
