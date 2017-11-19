package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.domain.FieldStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FieldStatusMapperTest {

    @Resource
    private FieldStatusMapper fieldStatusMapper;

    public static final Logger LOGGER = LoggerFactory.getLogger(FieldStatusMapperTest.class);

    @Test
    public void insertFieldStatus() throws Exception {
        Field field = new Field();
        field.setFieldId("test");

        int res = fieldStatusMapper.insertFieldStatus(field);

        Assert.assertEquals(1, res);
    }

    @Test
    public void selectFieldStatus() throws Exception {
        FieldStatus fieldStatuses = fieldStatusMapper.selectFieldStatus("f1701001");

        LOGGER.info("fieldStatuses: {}", fieldStatuses);
    }

    @Test
    public void deleteFieldStatus() throws Exception {
        Field field = new Field();
        field.setFieldId("f1701001");

        int res = fieldStatusMapper.deleteFieldStatus(field);

        Assert.assertEquals(1, res);
    }

    @Test
    public void updateFieldStatus() throws Exception {
        try {
            fieldStatusMapper.updateFieldStatus("12");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
