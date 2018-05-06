package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.Field;
import com.njfu.ia.sys.domain.Sensor;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.SensorMapper;
import com.njfu.ia.sys.service.SensorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    @Resource
    private SensorMapper sensorMapper;

    /**
     * 获取传感器列表
     *
     * @param sensor
     * @param field
     * @return data
     */
    @Override
    public List<Sensor> querySensors(Sensor sensor, Field field) {
        sensor.setField(field);
        return sensorMapper.selectSensors(sensor);
    }

    /**
     * 新增传感器信息
     *
     * @param sensor
     * @param field
     */
    @Override
    public void addSensor(Sensor sensor, Field field) {
        this.convertNull(sensor, field);
        int res = sensorMapper.insertSensor(sensor);
        if (res <= 0) {
            throw new BusinessException("新增车辆信息失败，请检查新增编号是否存在！");
        }
    }

    /**
     * 修改传感器信息
     *
     * @param sensor
     * @param field
     */
    @Override
    public void modifySensor(Sensor sensor, Field field) {
        this.convertNull(sensor, field);
        int res = sensorMapper.updateSensor(sensor);
        if (res <= 0) {
            throw new BusinessException("修改传感器信息失败！");
        }
    }

    /**
     * 删除传感器信息
     *
     * @param sensor
     */
    @Override
    public void removeSensor(Sensor sensor) {
        int res = sensorMapper.deleteSensor(sensor);
        if (res <= 0) {
            throw new BusinessException("删除传感器信息失败！");
        }
    }

    /**
     * 使得fieldId、sensorPs不为空字符串
     *
     * @param sensor sensorPs
     * @param field  fieldId
     */
    private void convertNull(Sensor sensor, Field field) {
        if ("".equals(sensor.getSensorPs())) {
            sensor.setSensorPs(null);
        }
    }
}
