package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.mapper.*;
import com.njfu.wa.sys.service.BlockService;
import com.njfu.wa.sys.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    @Resource
    private BlockMapper blockMapper;

    @Resource
    private FieldMapper fieldMapper;

    @Resource
    private FieldStatusMapper fieldStatusMapper;

    @Resource
    private MachineMapper machineMapper;

    @Resource
    private VehicleMapper vehicleMapper;

    @Resource
    private SensorMapper sensorMapper;

    /**
     * 获取地块列表
     *
     * @param block blockId blockName
     * @return data
     */
    @Override
    public List<Block> getBlocks(Block block) {
        return blockMapper.selectBlocks(block);
    }

    /**
     * 查询所有地块及各地块下处于使用中状态的大棚
     *
     * @return data
     */
    @Override
    public List<Block> getBlocksAndFields() {
        return blockMapper.selectBlocksAndFields();
    }

    /**
     * 新增地块信息
     *
     * @param block blockId blockName blockLoc blockPs
     */
    @Override
    public void addBlock(Block block) {
        // 若blockPs为空字符串，转为null
        if ("".equals(block.getBlockPs())) {
            block.setBlockPs(null);
        }
        int count = blockMapper.insertBlock(block);
        if (count <= 0) {
            throw new BusinessException("新增地块信息失败");
        }
    }

    /**
     * 修改地块信息
     *
     * @param block blockId blockName blockLoc blockPs
     */
    @Override
    public void modifyBlock(Block block) {
        // 若blockPs为空字符串，转为null
        if ("".equals(block.getBlockPs())) {
            block.setBlockPs(null);
        }
        int res = blockMapper.updateBlock(block);
        if (res <= 0) {
            throw new BusinessException("修改地块信息失败!");
        }
    }

    /**
     * 删除地块信息
     *
     * @param block blockId
     */
    @Override
    @Transactional
    public void removeBlock(Block block) {
        int delBlock = blockMapper.deleteBlock(block);
        if (delBlock <= 0) {
            throw new BusinessException("删除地块信息失败!");
        }

        // 删除大棚
        Field field = new Field();
        field.setBlock(block);
        field.setCrop(new Crop());
        List<Field> fields = fieldMapper.selectFields(field);
        for (Field f : fields) {
            // 删除大棚信息
            int delField = fieldMapper.deleteField(f);
            if (delField <= 0) {
                throw new BusinessException("删除大棚信息失败!");
            }
            // 删除大棚数据项
            int delFieldStatus = fieldStatusMapper.deleteFieldStatus(f);
            if (delFieldStatus <= 0) {
                throw new BusinessException("删除大棚数据项信息失败!");
            }
            // 将传感器所属大棚信息置空
            int updSensor = sensorMapper.updateSensorField(f.getFieldId());
            if (updSensor <= 0) {
                throw new BusinessException("传感器所属大棚信息置空失败!");
            }
        }

        // 车辆、机械所属地块信息置空
        String blockId = block.getBlockId();
        int updMachine = machineMapper.updateMachineByBlock(blockId);
        if (updMachine <= 0) {
            throw new BusinessException("机械所属地块信息置空失败!");
        }
        int updVehicle = vehicleMapper.updateVehicleByBlock(blockId);
        if (updVehicle <= 0) {
            throw new BusinessException("车辆所属地块信息置空失败!");
        }
    }
}
