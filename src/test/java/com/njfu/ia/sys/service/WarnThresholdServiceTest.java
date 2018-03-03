package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.WarnThreshold;
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

    public static final Logger LOGGER = LoggerFactory.getLogger(WarnThresholdServiceTest.class);

    @Resource
    private WarnThresholdService warnThresholdService;

    @Test
    public void getWarnThreshold() throws Exception {
        WarnThreshold warnThreshold = new WarnThreshold();
        List<WarnThreshold> warnThresholds = warnThresholdService.getWarnThreshold(warnThreshold);

        LOGGER.info("warnThresholds: {}", warnThresholds);
    }

    @Test
    public void modifyWarnThreshold() throws Exception {
        WarnThreshold warnThreshold = new WarnThreshold();

        warnThreshold.setId(1);
        warnThreshold.setCeil(12.23);
        warnThreshold.setFloor(32.22);
        warnThreshold.setUseStatus("1");

        try {
            warnThresholdService.modifyWarnThreshold(warnThreshold);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}