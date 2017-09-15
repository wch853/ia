package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.util.Message;
import com.njfu.wa.sys.domain.util.MessageFactory;
import com.njfu.wa.sys.mapper.BlockMapper;
import com.njfu.wa.sys.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private MessageFactory messageFactory;

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
    public Message addBlock(Block block) {
        // 若blockPs为空字符串，转为null
        if ("".equals(block.getBlockPs())) {
            block.setBlockPs(null);
        }

        int res = blockMapper.insertBlock(block);

        if (res == 0) {
            return messageFactory.failMessage("新增地块信息失败，请检查新增编号是否存在！");
        }
        return messageFactory.successMessage("新增地块信息成功！");
    }

    /**
     * 修改地块信息
     *
     * @param block blockId blockName blockLoc blockPs
     * @return message
     */
    @Override
    public Message modifyBlock(Block block) {
        // 若blockPs为空字符串，转为null
        if ("".equals(block.getBlockPs())) {
            block.setBlockPs(null);
        }

        int res = blockMapper.updateBlock(block);

        if (res == 0) {
            return messageFactory.failMessage("修改地块信息失败!");
        }

        return messageFactory.successMessage("修改地块信息成功!");
    }

    /**
     * 删除地块信息
     *
     * @param block blockId
     * @return message
     */
    @Override
    public Message removeBlock(Block block) {
        int res = blockMapper.deleteBlock(block);

        if (res == 0) {
            return messageFactory.failMessage("删除地块信息失败!");
        }

        return messageFactory.successMessage("删除地块信息成功!");
    }
}
