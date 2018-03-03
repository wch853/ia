package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.FieldStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldStatusServiceTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(FieldStatusServiceTest.class);

    @Resource
    private FieldStatusService fieldStatusService;

    @Test
    public void getFieldStatusById() throws Exception {
        FieldStatus result = fieldStatusService.getFieldStatusById("f1701001");
        LOGGER.info("data: {}", result);
    }

}