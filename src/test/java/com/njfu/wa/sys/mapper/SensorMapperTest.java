package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.domain.Sensor;
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
public class SensorMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorMapperTest.class);
    @Resource
    private SensorMapper sensorMapper;

    @Test
    public void selectSensors() throws Exception {
        Sensor sensor = new Sensor();
        sensor.setField(new Field());

        List<Sensor> sensors = sensorMapper.selectSensors(sensor);

        LOGGER.info("sensors: {}", sensors);
    }

    @Test
    public void insertSensor() throws Exception {
        Sensor sensor = new Sensor();
        sensor.setSensorId("s-01-000");
        sensor.setSensorFunc("1");
        sensor.setSensorType("aaa001");
        sensor.setField(new Field());
        sensor.setUseStatus("0");
        sensor.setSensorPs("test");

        int res = sensorMapper.insertSensor(sensor);

        Assert.assertEquals(1, res);
    }

    @Test
    public void updateSensor() throws Exception {
        Sensor sensor = new Sensor();
        sensor.setSensorId("s-01-001");
        sensor.setSensorFunc("1");
        sensor.setSensorType("aaa001");
        sensor.setField(new Field());
        sensor.setUseStatus("0");
        sensor.setSensorPs("test");

        int res = sensorMapper.updateSensor(sensor);

        Assert.assertEquals(1, res);
    }

    @Test
    public void deleteSensor() throws Exception {
        Sensor sensor = new Sensor();
        sensor.setSensorId("s-01-001");

        int res = sensorMapper.deleteSensor(sensor);

        Assert.assertEquals(1, res);
    }

    @Test
    public void updateSensorField() throws Exception {
        int res = sensorMapper.updateSensorField("f1701001");

        Assert.assertNotEquals(0, res);
    }

    @Test
    public void selectSensorsByField() throws Exception {
        List<String> sensorIds = sensorMapper.selectSensorsByField("f1701001");
        LOGGER.info("sensorIds: {}", sensorIds);
    }

}
