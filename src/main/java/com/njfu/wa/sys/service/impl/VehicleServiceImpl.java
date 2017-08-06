package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Vehicle;
import com.njfu.wa.sys.mapper.VehicleMapper;
import com.njfu.wa.sys.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleMapper.selectVehicles();
    }
}
