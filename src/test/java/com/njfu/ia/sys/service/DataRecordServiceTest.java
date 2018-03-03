package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.ChartData;
import com.njfu.ia.sys.domain.DataRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DataRecordServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataRecordServiceTest.class);
    @Resource
    private DataRecordService dataRecordService;

    @Test
    public void getDataRecords() throws Exception {
        DataRecord dataRecord = new DataRecord();
        dataRecord.setFieldId("f17010010");
        List<DataRecord> dataRecords = dataRecordService.getDataRecords(dataRecord, null, null);
        LOGGER.info("dataRecords: {}", dataRecords);
    }

    @Test
    public void getChartData() throws Exception {
        String[] dataTypes = {"1", "2", "3", "4"};
        ChartData chartData = dataRecordService.getChartData(dataTypes, "f1701001");
        LOGGER.info("dateSize: {}, dataSize: {}, valueSize: {}",
                chartData.getDateList().size(), chartData.getDataMap().size(), chartData.getDataMap().get("1").size());
    }
}