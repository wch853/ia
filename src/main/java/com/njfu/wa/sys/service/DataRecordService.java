package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.ChartData;
import com.njfu.wa.sys.domain.DataRecord;
import com.njfu.wa.sys.exception.BusinessException;

import java.util.List;

public interface DataRecordService {

    /**
     * 查询数据记录列表
     *
     * @param dataRecord dataRecord
     * @param start      start
     * @param end        end
     * @return data
     */
    List<DataRecord> getDataRecords(DataRecord dataRecord, String start, String end);

    /**
     * 查询生成图标所需的数据
     *
     * @param dataTypes dataTypes
     * @param fieldId   fieldId
     * @return data
     * @throws BusinessException BusinessException
     */
    ChartData getChartData(String[] dataTypes, String fieldId) throws BusinessException;
}
