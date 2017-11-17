package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Vehicle;
import com.njfu.wa.sys.utils.Result;

import java.util.List;

public interface VehicleService {

    /**
     * 获取车辆列表
     *
     * @param vehicle vehicleId vehicleType useStatus
     * @param block   blockId
     * @return data
     */
    List<Vehicle> getVehicles(Vehicle vehicle, Block block);

    /**
     * 新增车辆信息
     *
     * @param vehicle vehicleId vehicleType useStatus vehiclePs
     * @param block   blockId
     * @return message
     */
    Result addVehicle(Vehicle vehicle, Block block);

    /**
     * 修改车辆信息
     *
     * @param vehicle vehicleId vehicleType useStatus vehiclePs
     * @param block   blockId
     * @return message
     */
    Result modifyVehicle(Vehicle vehicle, Block block);

    /**
     * 删除车辆信息
     *
     * @param vehicle vehicleId
     * @return message
     */
    Result removeVehicle(Vehicle vehicle);
}
