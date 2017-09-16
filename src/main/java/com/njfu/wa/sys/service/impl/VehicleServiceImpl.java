package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Vehicle;
import com.njfu.wa.sys.domain.util.Result;
import com.njfu.wa.sys.domain.util.ResultFactory;
import com.njfu.wa.sys.mapper.VehicleMapper;
import com.njfu.wa.sys.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private ResultFactory resultFactory;

    /**
     * 获取车辆列表
     *
     * @param vehicle vehicleId vehicleType useStatus
     * @param block   blockId
     * @return data
     */
    @Override
    public List<Vehicle> getVehicles(Vehicle vehicle, Block block) {
        vehicle.setBlock(block);
        return vehicleMapper.selectVehicles(vehicle);
    }

    /**
     * 新增车辆信息
     *
     * @param vehicle vehicleId vehicleType useStatus vehiclePs
     * @param block   blockId
     * @return message
     */
    @Override
    public Result addVehicle(Vehicle vehicle, Block block) {
        this.convertNull(vehicle, block);

        int res = vehicleMapper.insertVehicle(vehicle);

        if (res == 0) {
            return resultFactory.failMessage("新增车辆信息失败，请检查新增编号是否存在！");
        }
        return resultFactory.successMessage("新增车辆信息成功！");
    }

    /**
     * 修改车辆信息
     *
     * @param vehicle vehicleId vehicleType useStatus vehiclePs
     * @param block   blockId
     * @return message
     */
    @Override
    public Result modifyVehicle(Vehicle vehicle, Block block) {
        this.convertNull(vehicle, block);

        int res = vehicleMapper.updateVehicle(vehicle);

        if (res == 0) {
            return resultFactory.failMessage("修改车辆信息失败");
        }
        return resultFactory.successMessage("修改车辆信息成功！");
    }

    /**
     * 删除车辆信息
     *
     * @param vehicle vehicleId
     * @return message
     */
    @Override
    public Result removeVehicle(Vehicle vehicle) {
        int res = vehicleMapper.deleteVehicle(vehicle);

        if (res == 0) {
            return resultFactory.failMessage("删除车辆信息失败");
        }
        return resultFactory.successMessage("删除车辆信息成功！");
    }

    /**
     * 使得blockId、vehiclePs不为空字符串
     *
     * @param vehicle vehiclePs
     * @param block   blockId
     */
    private void convertNull(Vehicle vehicle, Block block) {
        if (!"".equals(block.getBlockId())) {
            vehicle.setBlock(block);
        } else {
            vehicle.setBlock(new Block());
        }

        if ("".equals(vehicle.getVehiclePs())) {
            vehicle.setVehiclePs(null);
        }
    }
}
