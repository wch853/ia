package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.WarnThreshold;
import com.njfu.wa.sys.utils.Result;
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
public class WarnThresholdServiceTest {

    public static final Logger log = LoggerFactory.getLogger(WarnThresholdServiceTest.class);

    @Resource
    private WarnThresholdService warnThresholdService;

    @Test
    public void getWarnThreshold() throws Exception {
        WarnThreshold warnThreshold = new WarnThreshold();
        List<WarnThreshold> warnThresholds = warnThresholdService.getWarnThreshold(warnThreshold);

        log.info("warnThresholds: {}", warnThresholds);
    }

    @Test
    public void modifyWarnThreshold() throws Exception {
        WarnThreshold warnThreshold = new WarnThreshold();

        warnThreshold.setId(1);
        warnThreshold.setCeil(12.23);
        warnThreshold.setFloor(32.22);
        warnThreshold.setUseStatus("1");

        Result result = warnThresholdService.modifyWarnThreshold(warnThreshold);

        log.info("result: {}", result);
    }

}