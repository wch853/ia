package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.EndDevice;
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
public class EndDeviceMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EndDeviceMapperTest.class);

    @Resource
    private EndDeviceMapper endDeviceMapper;

    @Test
    public void selectEndDevices() {
        List<EndDevice> endDevices = endDeviceMapper.selectEndDevices(new EndDevice());
        LOGGER.info("endDevices: {}", endDevices);
    }
}