package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.DataRecord;
import com.njfu.wa.sys.mapper.DataRecordMapper;
import com.njfu.wa.sys.mapper.SensorMapper;
import com.njfu.wa.sys.service.DataRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
