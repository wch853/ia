package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.*;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.*;
import com.njfu.ia.sys.service.BlockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    public void removeBlock(Integer id) {
        int count = blockMapper.deleteBlock(id);
        if (count <= 0) {
            throw new BusinessException("删除地块信息失败！");
        }
    }
}
