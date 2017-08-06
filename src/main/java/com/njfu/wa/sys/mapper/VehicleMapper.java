package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Vehicle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehicleMapper {

    List<Vehicle> selectVehicles();
}
