package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.DataRecord;
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

    @Resource
    private DataRecordService dataRecordService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataRecordServiceTest.class);

    @Test
    public void getDataRecords() throws Exception {
        DataRecord dataRecord = new DataRecord();
        dataRecord.setFieldId("f17010010");
        List<DataRecord> dataRecords = dataRecordService.getDataRecords(dataRecord, null, null);
        LOGGER.info("dataRecords: {}", dataRecords);
    }

}