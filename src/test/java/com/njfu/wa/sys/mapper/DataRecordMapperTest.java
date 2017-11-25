package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.DataRecord;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DataRecordMapperTest {

    @Resource
    private DataRecordMapper dataRecordMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataRecordMapperTest.class);

    @Test
    public void selectDataRecords() throws Exception {
        List<String> sensorIds = new ArrayList<>();
        sensorIds.add("s-01-001");
        sensorIds.add("s-01-002");

        Map<String, Object> map = new HashMap<>();
        map.put("sensorIds", sensorIds);

        List<DataRecord> dataRecords = dataRecordMapper.selectDataRecords(map);
        LOGGER.info("dataRecords: {}", dataRecords);
    }

    @Test
    public void selectChartData() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("dataTypes", Arrays.asList("1", "2", "3", "4"));
        map.put("date", DateUtils.addDays(new Date(), -7));
        map.put("sensorIds", Arrays.asList("s-01-001"));

        List<DataRecord> dataRecords = dataRecordMapper.selectChartData(map);
        LOGGER.info("dataRecords: {}", dataRecords);
    }
}