package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.WarnRecord;
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
public class WarnRecordServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarnRecordServiceTest.class);

    @Resource
    private WarnRecordService warnRecordService;

    @Test
    public void scanFieldStatusTest() {
        try {
            warnRecordService.scanFieldStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getWarnRecord() throws Exception {
        WarnRecord warnRecord = new WarnRecord();

        List<WarnRecord> warnRecords = warnRecordService.getWarnRecord(warnRecord, null, null);

        LOGGER.info("warnRecords: {}", warnRecords);
    }

    @Test
    public void getUnHandleWarnRecord() throws Exception {
        List<WarnRecord> result = warnRecordService.getUnHandleWarnRecord();

        LOGGER.info("result: {}", result);
    }

    @Test
    public void modifyWarnRecord() throws Exception {
        Integer[] ids = {1, 2, 3};
        String flag = "-2";

        try {
            warnRecordService.modifyWarnRecord(ids, flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUnhandleRecordCount() throws Exception {
        int result = warnRecordService.getUnhandleRecordCount();
        LOGGER.info("result: {}", result);
    }
}
