package com.njfu.ia.process.mapper;

import com.njfu.ia.process.domain.UploadDataRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadDataRecordMapper {

    /**
     * 批量插入上行数据
     *
     * @param uploadDataRecords uploadDataRecords
     * @return count
     */
    int batchInsertDataRecords(List<UploadDataRecord> uploadDataRecords);
}
