package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.util.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldStatusServiceTest {

    @Autowired
    private FieldStatusService fieldStatusService;

    public static final Logger log = LoggerFactory.getLogger(FieldStatusServiceTest.class);

    @Test
    public void getFieldStatusById() throws Exception {
        Message message = fieldStatusService.getFieldStatusById("f1701001");
        log.info("data: {}", message.getData());
    }

}