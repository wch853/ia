package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.DataRecord;

import java.util.List;

public interface DataRecordService {

    /**
     * 查询数据记录列表
     * @param dataRecord dataRecord
     * @param start start
     * @param end end
     * @return data
     */
    List<DataRecord> getDataRecords(DataRecord dataRecord, String start, String end);
}
