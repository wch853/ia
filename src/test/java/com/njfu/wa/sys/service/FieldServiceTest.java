package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.util.Message;
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
public class FieldServiceTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FieldService fieldService;

    @Test
    public void getFields() throws Exception {
        List<Field> fields = fieldService.getFields(new Field(), null, null);
        log.info("fields: {}", fields);
    }

    @Test
    public void addField() throws Exception {
        Message message = fieldService.addField(new Field(), null, null);
        log.info("message: {}", message);
    }


    @Test
    public void modifyField() throws Exception {
        Message message = fieldService.modifyField(new Field(), null, null);
        log.info("message: {}", message);
    }

    @Test
    public void removeField() throws Exception {
        Message message = fieldService.removeField("f1700001");
        log.info("message: {}", message);
    }
}