package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Sensor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SensorMapper {

    /**
     * 获取传感器列表
     *
     * @param sensor sensorId sensorFunc sensorType fieldId useStatus sensorPs
     * @return data
     */
    List<Sensor> selectSensors(Sensor sensor);

    /**
     * 新增传感器信息
     *
     * @param sensor sensorId sensorFunc sensorType fieldId useStatus sensorPs
     * @return row count
     */
    int insertSensor(Sensor sensor);

    /**
     * 修改传感器信息
     *
     * @param sensor sensorId sensorFunc sensorType fieldId useStatus sensorPs
     * @return row count
     */
    int updateSensor(Sensor sensor);

    /**
     * 删除传感器信息
     *
     * @param sensor sensorId
     * @return row count
     */
    int deleteSensor(Sensor sensor);

    /**
     * 将对应传感器的fieldId置空
     *
     * @param fieldId
     * @return
     */
    int updateSensorField(@Param("fieldId") String fieldId);
}
