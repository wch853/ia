package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.domain.Sensor;
import com.njfu.wa.sys.utils.Result;
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
public class SensorServiceTest {

    private static final Logger log = LoggerFactory.getLogger(SensorServiceTest.class);

    @Resource
    private SensorService sensorService;

    @Test
    public void getSensors() throws Exception {
        List<Sensor> sensors = sensorService.getSensors(new Sensor(), new Field());

        log.info("sensors: {}", sensors);
    }

    @Test
    public void addSensor() throws Exception {
        Sensor sensor = new Sensor();
        sensor.setSensorId("s-01-000");
        sensor.setSensorFunc("1");
        sensor.setSensorType("aaa001");
        sensor.setUseStatus("0");
        sensor.setSensorPs("test");

        Result result = sensorService.addSensor(sensor, new Field());

        log.info("result: {}", result);
    }

    @Test
    public void modifySensor() throws Exception {
        Sensor sensor = new Sensor();
        sensor.setSensorId("s-01-001");
        sensor.setSensorFunc("1");
        sensor.setSensorType("aaa001");
        sensor.setUseStatus("0");
        sensor.setSensorPs("test");

        Result result = sensorService.modifySensor(sensor, new Field());

        log.info("result: {}", result);
    }

    @Test
    public void removeSensor() throws Exception {
        Sensor sensor = new Sensor();
        sensor.setSensorId("s-01-001");

        Result result = sensorService.removeSensor(sensor);

        log.info("result: {}", result);
    }

}