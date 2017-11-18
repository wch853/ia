package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Vehicle;

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
     */
    void addVehicle(Vehicle vehicle, Block block);

    /**
     * 修改车辆信息
     *
     * @param vehicle vehicleId vehicleType useStatus vehiclePs
     * @param block   blockId
     */
    void modifyVehicle(Vehicle vehicle, Block block);

    /**
     * 删除车辆信息
     *
     * @param vehicle vehicleId
     */
    void removeVehicle(Vehicle vehicle);
}
