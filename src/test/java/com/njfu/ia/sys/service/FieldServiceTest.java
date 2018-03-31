package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Block;
import com.njfu.ia.sys.domain.Crop;
import com.njfu.ia.sys.domain.Field;
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
public class FieldServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldServiceTest.class);

    @Resource
    private FieldService fieldService;

    @Test
    public void getFields() throws Exception {
        List<Field> fields = fieldService.getFields(new Field());
        LOGGER.info("fields: {}", fields);
    }

    @Test
    public void addField() throws Exception {
        Field field = new Field();
        field.setFieldName("test");

        Block block = new Block();

        try {
            fieldService.addField(field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void modifyField() throws Exception {
        Field field = new Field();
        field.setFieldName("test");

        Block block = new Block();

        try {
            fieldService.modifyField(new Field());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeField() throws Exception {

        try {
            fieldService.removeField(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}