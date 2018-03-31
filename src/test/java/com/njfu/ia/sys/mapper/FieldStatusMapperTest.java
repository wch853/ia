package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Field;
import com.njfu.ia.sys.domain.FieldStatus;
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

    public static final Logger LOGGER = LoggerFactory.getLogger(FieldStatusMapperTest.class);
    @Resource
    private FieldStatusMapper fieldStatusMapper;

    @Test
    public void insertFieldStatus() throws Exception {
        Field field = new Field();

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
