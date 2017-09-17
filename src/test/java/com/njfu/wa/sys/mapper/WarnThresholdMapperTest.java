package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.WarnThreshold;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WarnThresholdMapperTest {

    @Autowired
    private WarnThresholdMapper warnThresholdMapper;

    public static final Logger log = LoggerFactory.getLogger(WarnThresholdMapperTest.class);

    @Test
    public void selectWarnThreshold() throws Exception {
        List<WarnThreshold> warnThresholds = warnThresholdMapper.selectWarnThreshold(null);

        log.info("warnThresholds: {}", warnThresholds);
    }

    @Test
    public void updateWarnThreshold() throws Exception {
        WarnThreshold warnThreshold = new WarnThreshold();
        warnThreshold.setId(1);
        warnThreshold.setCeil(12.23);
        warnThreshold.setFloor(34.21);
        warnThreshold.setUseStatus("1");

        int res = warnThresholdMapper.updateWarnThreshold(warnThreshold);

        Assert.assertEquals(1, res);
    }

}