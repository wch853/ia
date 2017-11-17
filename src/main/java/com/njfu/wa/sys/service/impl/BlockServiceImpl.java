package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.domain.Field;
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
     * @return message
     */
    @Override
    public Result addBlock(Block block) {
        // 若blockPs为空字符串，转为null
        if ("".equals(block.getBlockPs())) {
            block.setBlockPs(null);
        }

        int res = blockMapper.insertBlock(block);

        if (res == 0) {
            return Result.fail("新增地块信息失败，请检查新增编号是否存在！");
        }
        return Result.success("新增地块信息成功！");
    }

    /**
     * 修改地块信息
     *
     * @param block blockId blockName blockLoc blockPs
     * @return message
     */
    @Override
    public Result modifyBlock(Block block) {
        // 若blockPs为空字符串，转为null
        if ("".equals(block.getBlockPs())) {
            block.setBlockPs(null);
        }

        int res = blockMapper.updateBlock(block);

        if (res == 0) {
            return Result.fail("修改地块信息失败!");
        }

        return Result.success("修改地块信息成功!");
    }

    /**
     * 删除地块信息
     *
     * @param block blockId
     * @return message
     */
    @Override
    @Transactional
    public Result removeBlock(Block block) {
        int res = blockMapper.deleteBlock(block);

        if (res == 0) {
            return Result.fail("删除地块信息失败!");
        }

        // 删除大棚
        Field field = new Field();
        field.setBlock(block);
        field.setCrop(new Crop());
        List<Field> fields = fieldMapper.selectFields(field);
        for (Field f : fields) {
            // 删除大棚信息
            fieldMapper.deleteField(f);
            // 删除大棚数据项
            fieldStatusMapper.deleteFieldStatus(f);
            // 将传感器所属大棚信息置空
            sensorMapper.updateSensorField(f.getFieldId());
        }

        // 车辆、机械所属地块信息置空
        String blockId = block.getBlockId();
        machineMapper.updateMachineByBlock(blockId);
        vehicleMapper.updateVehicleByBlock(blockId);

        return Result.success("删除地块信息成功!");
    }
}
