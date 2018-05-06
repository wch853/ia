package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.ChartData;
import com.njfu.ia.sys.domain.UpstreamDataRecord;
import com.njfu.ia.sys.utils.JsonUtils;
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
public class UpstreamDataRecordServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpstreamDataRecordServiceTest.class);

    @Resource
    private UpstreamDataRecordService upstreamDataRecordService;

    @Test
    public void getDataRecords() {
        List<UpstreamDataRecord> records = upstreamDataRecordService.queryDataRecords(new UpstreamDataRecord(), null, null);
        LOGGER.info("records: {}", JsonUtils.toJsonString(records));
    }

    @Test
    public void getLastDataRecords() {
        List<UpstreamDataRecord> lastDataRecords = upstreamDataRecordService.queryLastDataRecords(1);
        LOGGER.info("data: {}", JsonUtils.toJsonString(lastDataRecords));
    }

    @Test
    public void constructChartData() {
        ChartData chartData = upstreamDataRecordService.constructChartData(new Integer[]{1, 3, 5}, 16, null, null);
        LOGGER.info("chart data: {}", JsonUtils.toJsonString(chartData));
    }
}