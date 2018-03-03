package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.WarnRecord;
import com.njfu.ia.sys.enums.WarnRecordFlagEnum;
import org.junit.Assert;
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
public class WarnRecordMapperTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(WarnThresholdMapperTest.class);

    @Resource
    private WarnRecordMapper warnRecordMapper;

    @Test
    public void insertWarnRecord() throws Exception {
        WarnRecord warnRecord = new WarnRecord();
        warnRecord.setFieldId("f1701001");
        warnRecord.setWarnType("1");
        warnRecord.setWarnVal(15.36);

        int res = warnRecordMapper.insertWarnRecord(warnRecord);

        Assert.assertEquals(1, res);
    }

    @Test
    public void selectWarnRecord() throws Exception {
        WarnRecord warnRecord = new WarnRecord();
        String start = "2017-09-18";

        List<WarnRecord> warnRecords = warnRecordMapper.selectWarnRecord(warnRecord, start, null);

        LOGGER.info("warnRecords: {}", warnRecords);
    }

    @Test
    public void selectWarnRecordByFlag() throws Exception {
        List<WarnRecord> warnRecords = warnRecordMapper.selectWarnRecordByFlag(WarnRecordFlagEnum.UNHANDLE.code());

        LOGGER.info("warnRecords: {}", warnRecords);
    }

    @Test
    public void updateWarnRecord() throws Exception {
        WarnRecord warnRecord = new WarnRecord();
        warnRecord.setId(1);
        warnRecord.setFlag("1");

        Integer[] ids = {1, 2, 3};
        String flag = "-2";

        int res = warnRecordMapper.updateWarnRecord(ids, flag);
        Assert.assertNotEquals(1, res);
    }

    @Test
    public void checkWarn() throws Exception {
        warnRecordMapper.checkWarn();
    }
}
