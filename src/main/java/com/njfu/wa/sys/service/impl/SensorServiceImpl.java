package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.domain.Sensor;
import com.njfu.wa.sys.utils.Result;
import com.njfu.wa.sys.mapper.SensorMapper;
import com.njfu.wa.sys.service.SensorService;
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
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return data
     */
    @Override
    public List<Sensor> getSensors(Sensor sensor, Field field) {
        sensor.setField(field);
        return sensorMapper.selectSensors(sensor);
    }

    /**
     * 新增传感器信息
     *
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return message
     */
    @Override
    public Result addSensor(Sensor sensor, Field field) {
        this.convertNull(sensor, field);

        int res = sensorMapper.insertSensor(sensor);

        if (res == 0) {
            return Result.fail("新增车辆信息失败，请检查新增编号是否存在！");
        }

        return Result.success("新增车辆信息成功！");
    }

    /**
     * 修改传感器信息
     *
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return message
     */
    @Override
    public Result modifySensor(Sensor sensor, Field field) {
        this.convertNull(sensor, field);

        int res = sensorMapper.updateSensor(sensor);

        if (res == 0) {
            return Result.fail("修改传感器信息失败！");
        }

        return Result.success("修改传感器信息成功！");
    }

    /**
     * 删除传感器信息
     *
     * @param sensor sensorId
     * @return messgae
     */
    @Override
    public Result removeSensor(Sensor sensor) {

        int res = sensorMapper.deleteSensor(sensor);

        if (res == 0) {
            return Result.fail("删除传感器信息失败！");
        }

        return Result.success("删除传感器信息成功！");
    }

    /**
     * 使得fieldId、sensorPs不为空字符串
     *
     * @param sensor sensorPs
     * @param field  fieldId
     */
    private void convertNull(Sensor sensor, Field field) {
        if (!"".equals(field.getFieldId())) {
            sensor.setField(field);
        } else {
            sensor.setField(new Field());
        }

        if ("".equals(sensor.getSensorPs())) {
            sensor.setSensorPs(null);
        }
    }
}
