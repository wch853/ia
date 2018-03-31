package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Block;
import com.njfu.ia.sys.domain.Crop;
import com.njfu.ia.sys.domain.Field;
import org.junit.Assert;
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
public class FieldMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldMapperTest.class);

    @Resource
    private FieldMapper fieldMapper;

    @Test
    public void selectFields() throws Exception {
        Field field = new Field();

        List<Field> fields = fieldMapper.selectFields(field);
        LOGGER.info("fields: {}", fields);
    }

    @Test
    public void insertField() throws Exception {
        Field field = new Field();
        field.setFieldName("test");

        Block block = new Block();

        int res = fieldMapper.insertField(field);

        Assert.assertEquals(1, res);
    }

    @Test
    public void updateField() throws Exception {
        Field field = new Field();
        field.setFieldName("test");

        Block block = new Block();

        int res = fieldMapper.updateField(field);
        Assert.assertEquals(1, res);
    }

    @Test
    public void deleteField() throws Exception {
        int res = fieldMapper.deleteField(1);

        Assert.assertEquals(1, res);
    }

}