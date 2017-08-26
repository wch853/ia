package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
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
     * @return
     */
    int deleteVehicle(Vehicle vehicle);
}
