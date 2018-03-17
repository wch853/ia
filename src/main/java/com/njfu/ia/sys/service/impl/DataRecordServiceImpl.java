package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.ChartData;
import com.njfu.ia.sys.domain.DataRecord;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.DataRecordMapper;
import com.njfu.ia.sys.mapper.SensorMapper;
import com.njfu.ia.sys.service.DataRecordService;
import com.njfu.ia.sys.utils.Constants;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DataRecordServiceImpl implements DataRecordService {

    @Resource
    private DataRecordMapper dataRecordMapper;

    @Resource
    private SensorMapper sensorMapper;

    /**
     * 查询数据记录列表
     *
     * @param dataRecord dataRecord
     * @param start      start
     * @param end        end
     * @return data
     */
    @Override
    @Transactional
    public List<DataRecord> getDataRecords(DataRecord dataRecord, String start, String end) {
        // 有大棚作为查询条件时，查询大棚下的所有传感器
        String fieldId = dataRecord.getFieldId();
        List<String> sensorIds = null;
        if (null != fieldId && !StringUtils.isEmpty(fieldId)) {
            sensorIds = sensorMapper.selectSensorsByField(fieldId);
            if (CollectionUtils.isEmpty(sensorIds)) {
                // 不存在指定大棚下的传感器
                sensorIds.add(null);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("sensorId", dataRecord.getSensorId());
        map.put("dataType", dataRecord.getDataType());
        map.put("sensorIds", sensorIds);
        map.put("start", start);
        map.put("end", end);
        return dataRecordMapper.selectDataRecords(map);
    }

    /**
     * 查询生成图表所需的数据
     *
     * @param dataTypes dataTypes
     * @param fieldId   fieldId
     * @return data
     */
    @Override
    public ChartData getChartData(String[] dataTypes, String fieldId) throws BusinessException {
        ChartData chartData = new ChartData();
        // 一周小时数
        int hoursOfAWeek = 7 * 24;
        List<Date> dateList = this.getDateList(hoursOfAWeek);
        List<String> sensorIds = sensorMapper.selectSensorsByField(fieldId);
        if (CollectionUtils.isEmpty(sensorIds)) {
            throw new BusinessException("该大棚下没有关联传感器，无法获取数据！");
        }

        // 处理查询参数
        List<String> types = Arrays.asList(dataTypes);
        Map<String, Object> map = new HashMap<>();
        map.put("dataTypes", types);
        map.put("sensorIds", sensorIds);
        map.put("date", dateList.get(0));

        List<DataRecord> dataRecords = dataRecordMapper.selectChartData(map);
        this.classifyData(chartData, dataRecords, types, dateList);
        return chartData;
    }

    /**
     * 通过指定小时数获取日期列表
     *
     * @param hours hours
     * @return list
     */
    private List<Date> getDateList(int hours) {
        List<Date> dateList = new ArrayList<>();
        // 当前时间整点
        Date now = DateUtils.round(new Date(), Calendar.HOUR);
        // 距离指定小时数的整点
        Date earliestDate = DateUtils.addHours(now, -hours);
        // 递增小时获取日期列表
        for (Date date = earliestDate; date.compareTo(now) < 0; date = DateUtils.addHours(date, 1)) {
            dateList.add(date);
        }
        return dateList;
    }

    /**
     * 将DateList转为指定格式的StringList
     *
     * @param dateList dateList
     * @return list
     */
    private List<String> formatDateList(List<Date> dateList) {
        List<String> formatDateList = new ArrayList<>();
        for (Date date : dateList) {
            String format = DateFormatUtils.format(date, Constants.DATE_MINUTE_FORMAT);
            formatDateList.add(format);
        }
        return formatDateList;
    }

    /**
     * 整理数据
     *
     * @param chartData   chartData
     * @param dataRecords dataRecords
     * @param dataTypes   dataTypes
     * @param dateList    dateList
     * @throws BusinessException BusinessException
     */
    private void classifyData(ChartData chartData, List<DataRecord> dataRecords,
                              List<String> dataTypes, List<Date> dateList) throws BusinessException {
        if (CollectionUtils.isEmpty(dataRecords)) {
            throw new BusinessException("该大棚未上传此类有效数据！");
        }
        // 数据类型-时间点-数据值
        Map<String, Map<Date, Double>> typeToDateToValue = new HashMap<>(dataTypes.size());

        // 根据查询数据类型初始化查询结果容器
        for (String dataType : dataTypes) {
            typeToDateToValue.put(dataType, new HashMap<>());
        }

        // 获取当前时间整点
        Date now = DateUtils.round(new Date(), Calendar.HOUR);

        // 对传入的所有数据记录进行过滤
        for (DataRecord dataRecord : dataRecords) {
            // 获取记录类型
            String dataType = dataRecord.getDataType();
            if (!typeToDateToValue.containsKey(dataType)) {
                // 该条记录不在此次查询任务范围内，跳过
                continue;
            }
            // 获取记录时间整点
            Date recordTime = DateUtils.round(dataRecord.getRecordTime(), Calendar.HOUR);
            if (now.compareTo(recordTime) <= 0) {
                // 滤去大于等于当前时间点的数据
                continue;
            }
            // 存放查询结果
            typeToDateToValue.get(dataType).put(recordTime, dataRecord.getVal());
        }

        // 将 数据类型-时间点-数据值 拆分为 时间点列表、数据类型-数据值
        Map<String, List<Double>> typeToValue = new HashMap<>();
        for (Map.Entry<String, Map<Date, Double>> entry : typeToDateToValue.entrySet()) {
            // 数据类型
            String dataType = entry.getKey();
            // 时间点-数值
            Map<Date, Double> dateToValue = entry.getValue();
            List<Double> values = new ArrayList<>();
            // 逐一遍历每个时间点，将对应的值加入集合
            for (Date date : dateList) {
                // 该时间点没有数据，需要用0补全
                values.add(dateToValue.getOrDefault(date, Double.NaN));
            }
            typeToValue.put(dataType, values);
        }
        chartData.setDateList(formatDateList(dateList));
        chartData.setDataMap(typeToValue);
    }
}
