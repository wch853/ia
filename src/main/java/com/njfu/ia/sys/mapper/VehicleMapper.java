package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VehicleMapper {

    /**
     * 获取车辆列表
     *
     * @param vehicle vehicleId vehicleType blockId useStatus
     * @return data
     */
    List<Vehicle> selectVehicles(Vehicle vehicle);

    /**
     * 新增车辆信息
     *
     * @param vehicle vehicleId vehicleType blockId useStatus vehiclePs
     * @return row count
     */
    int insertVehicle(Vehicle vehicle);

    /**
     * 修改车辆信息
     *
     * @param vehicle vehicleId vehicleType blockId useStatus vehiclePs
     * @return row count
     */
    int updateVehicle(Vehicle vehicle);

    /**
     * 删除车辆信息
     *
     * @param vehicle vehicleId
     * @return row count
     */
    int deleteVehicle(Vehicle vehicle);

    /**
     * 将属于某地块下的车辆所属大棚置空
     *
     * @param blockId blockId
     * @return row count
     */
    int updateVehicleByBlock(@Param("blockId") String blockId);
}
