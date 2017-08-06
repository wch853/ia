package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Sensor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SensorMapper {

    List<Sensor> selectSensors();
}
