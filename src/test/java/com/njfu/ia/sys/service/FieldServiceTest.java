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
        List<Field> fields = fieldService.getFields(new Field(), new Block(), new Crop());
        LOGGER.info("fields: {}", fields);
    }

    @Test
    public void addField() throws Exception {
        Field field = new Field();
        field.setFieldId("f1700001");
        field.setFieldName("test");
        field.setUseStatus("0");
        field.setFieldPs("test");

        Block block = new Block();
        block.setBlockId("b01");

        try {
            fieldService.addField(field, block, new Crop());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void modifyField() throws Exception {
        Field field = new Field();
        field.setFieldId("f1701001");
        field.setFieldName("test");
        field.setUseStatus("0");

        Block block = new Block();
        block.setBlockId("b01");

        try {
            fieldService.modifyField(new Field(), block, new Crop());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeField() throws Exception {
        Field field = new Field();
        field.setFieldId("f1701001");

        try {
            fieldService.removeField(field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}