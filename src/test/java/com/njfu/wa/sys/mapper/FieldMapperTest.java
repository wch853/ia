package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.domain.Field;
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
public class FieldMapperTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FieldMapper fieldMapper;

    @Test
    public void selectFields() throws Exception {
        Field field = new Field();
        field.setBlock(new Block(null));
        field.setCrop(new Crop(null));
        field.setUseStatus("1");
        List<Field> fields = fieldMapper.selectFields(field);
        log.info("fields : {}", fields);
    }

    @Test
    public void insertField() throws Exception {
        Field field = new Field();
        field.setFieldId("f1701001");
        field.setFieldName("测试温室");
        field.setBlock(new Block("b00"));
//        field.setCrop(new Crop("c00"));
        field.setUseStatus("0");
//        field.setFieldPs("测试备注");

        int rowCount = fieldMapper.insertField(field);

        log.info("rowCount : {}", rowCount);
    }

    @Test
    public void updateField() throws Exception {
        Field field = new Field();
        field.setFieldId("f1701001");
        field.setFieldName("测试温室");
        field.setBlock(new Block("b00"));
        field.setUseStatus("0");

        int rowCount = fieldMapper.updateField(field);
        log.info("rowCount : {}", rowCount);
    }

    @Test
    public void deleteField() throws Exception {
        int rowCount = fieldMapper.deleteField("f1700001");
        log.info("rowCount : {}", rowCount);
    }

}