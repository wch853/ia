package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.ChartData;
import com.njfu.ia.sys.domain.DataRecord;
import com.njfu.ia.sys.exception.BusinessException;

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
     * 查询生成图表所需的数据
     *
     * @param dataTypes dataTypes
     * @param fieldId   fieldId
     * @return data
     * @throws BusinessException BusinessException
     */
    ChartData getChartData(String[] dataTypes, String fieldId) throws BusinessException;
}
