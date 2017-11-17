package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.domain.Sensor;
import com.njfu.wa.sys.utils.Result;

import java.util.List;

public interface SensorService {

    /**
     * 获取传感器列表
     *
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return data
     */
    List<Sensor> getSensors(Sensor sensor, Field field);

    /**
     * 新增传感器信息
     *
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return message
     */
    Result addSensor(Sensor sensor, Field field);

    /**
     * 修改传感器信息
     *
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return message
     */
    Result modifySensor(Sensor sensor, Field field);

    /**
     * 删除传感器信息
     *
     * @param sensor sensorId
     * @return message
     */
    Result removeSensor(Sensor sensor);
}
