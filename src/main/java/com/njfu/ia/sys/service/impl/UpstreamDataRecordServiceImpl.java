package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.ChartData;
import com.njfu.ia.sys.domain.UpstreamDataRecord;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.UpstreamDataRecordMapper;
import com.njfu.ia.sys.service.UpstreamDataRecordService;
import com.njfu.ia.sys.utils.Constants;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UpstreamDataRecordServiceImpl implements UpstreamDataRecordService {

    @Resource
    private UpstreamDataRecordMapper upstreamDataRecordMapper;

    /**
     * 查询上传数据记录
     *
     * @param dataRecord
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<UpstreamDataRecord> queryDataRecords(UpstreamDataRecord dataRecord, String start, String end) {
        return upstreamDataRecordMapper.selectDataRecords(dataRecord, start, end);
    }

    /**
     * 根据区块编号查询近期数据上传记录
     *
     * @param sectionId
     * @return
     */
    @Override
    public List<UpstreamDataRecord> queryLastDataRecords(Integer sectionId) {
        List<UpstreamDataRecord> result = new ArrayList<>();
        List<UpstreamDataRecord> lastReceiveRecords =
                upstreamDataRecordMapper.selectLastReceiveTime(sectionId, Constants.DEFAULT_QUERY_GAP);
        for (UpstreamDataRecord record : lastReceiveRecords) {
            Integer dataType = record.getDataType();
            Date receiveTime = record.getReceiveTime();
            result.addAll(upstreamDataRecordMapper
                    .selectLastDataRecords(sectionId, dataType, receiveTime, Constants.IN_USE_STATUS));
        }
        return result;
    }

    @Override
    public ChartData constructChartData(Integer[] dataTypes, Integer sectionId, String start, String end) {
        if (null == dataTypes || dataTypes.length == 0 || null == sectionId) {
            throw new BusinessException("查询条件缺失！");
        }

        Date now = new Date();
        Date startDate = null;
        try {
            startDate = DateUtils.parseDate(start, Constants.DATE_SECOND_FORMAT);
        } catch (Exception e) {
            startDate = DateUtils.addMinutes(now, -Constants.SECONDS_OF_WEEK);
        }
        if (null == start) {
            start = DateFormatUtils.format(startDate, Constants.DATE_SECOND_FORMAT);
        }
        if (null == end) {
            end = DateFormatUtils.format(now, Constants.DATE_SECOND_FORMAT);
        }

        // 查询数据：数据类型-时间点-数据值
        Map<String, Map<Date, Double>> typeToDateToValue = new HashMap<>(dataTypes.length);
        UpstreamDataRecord record = new UpstreamDataRecord();
        record.setSectionId(sectionId);
        for (Integer dataType : dataTypes) {
            record.setDataType(dataType);
            List<UpstreamDataRecord> records = upstreamDataRecordMapper.selectDataRecords(record, start, end);

            if (!CollectionUtils.isEmpty(records)) {
                Map<Date, Double> dateToValue = new HashMap<>();
                String dataTypeName = null;
                for (UpstreamDataRecord dataRecord : records) {
                    if (null == dataTypeName) {
                        dataTypeName = dataRecord.getDataTypeName();
                    }
                    // 数据上传时间整点
                    Date upPunctual = DateUtils.round(dataRecord.getReceiveTime(), Calendar.HOUR);
                    if (now.compareTo(upPunctual) <= 0) {
                        continue;
                    }
                    dateToValue.put(upPunctual, dataRecord.getValue());
                }

                typeToDateToValue.put(dataTypeName, dateToValue);
            }
        }

        // 查询时间整点集合
        List<Date> dateList = new ArrayList<>();
        List<String> formatDateList = new ArrayList<>();
        // 查询开始时间整点
        Date earliestDate = DateUtils.round(startDate, Calendar.HOUR);
        // 递增小时获取日期列表
        for (Date date = earliestDate; date.compareTo(now) < 0; date = DateUtils.addHours(date, 1)) {
            dateList.add(date);
            formatDateList.add(DateFormatUtils.format(date, Constants.DATE_MINUTE_FORMAT));
        }

        // 数据类型名称-数据值
        Map<String, List<Double>> typeToValue = new HashMap<>();
        for (Map.Entry<String, Map<Date, Double>> entry : typeToDateToValue.entrySet()) {
            // 数据类型
            String dataTypeName = entry.getKey();
            // 时间点-数值
            Map<Date, Double> dateToValue = entry.getValue();
            List<Double> values = new ArrayList<>();
            // 逐一遍历每个时间点，将对应的值加入集合
            for (Date date : dateList) {
                // 该时间点没有数据，需要用0补全
                values.add(dateToValue.getOrDefault(date, Double.NaN));
            }
            typeToValue.put(dataTypeName, values);
        }

        return new ChartData(formatDateList, typeToValue);
    }
}
