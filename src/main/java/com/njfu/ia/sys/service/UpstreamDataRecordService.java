package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.ChartData;
import com.njfu.ia.sys.domain.UpstreamDataRecord;

import java.util.List;

public interface UpstreamDataRecordService {

    /**
     * 查询上传数据记录
     *
     * @param dataRecord
     * @param start
     * @param end
     * @return
     */
    List<UpstreamDataRecord> queryDataRecords(UpstreamDataRecord dataRecord, String start, String end);


    /**
     * 根据区块编号查询近期数据上传记录
     *
     * @param sectionId
     * @return
     */
    List<UpstreamDataRecord> queryLastDataRecords(Integer sectionId);

    /**
     * 构造图表数据
     *
     * @param dataTypes
     * @param sectionId
     * @param start
     * @param end
     * @return
     */
    ChartData constructChartData(Integer[] dataTypes, Integer sectionId, String start, String end);
}
