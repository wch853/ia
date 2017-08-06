package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Sensor;
import com.njfu.wa.sys.mapper.SensorMapper;
import com.njfu.wa.sys.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorMapper sensorMapper;

    @Override
    public List<Sensor> getSensors() {
        return sensorMapper.selectSensors();
    }
}
