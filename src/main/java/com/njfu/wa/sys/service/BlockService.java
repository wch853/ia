package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.util.Message;

import java.util.List;

public interface BlockService {

    /**
     * 获取地块信息
     *
     * @param block block
     * @return data
     */
    List<Block> getBlocks(Block block);

    /**
     * index页获取所有地块信息及相应地块下使用状态的大棚
     *
     * @return data
     */
    List<Block> getBlocksAndFields();

    /**
     * 新增地块信息
     *
     * @param block block
     * @return message
     */
    Message addBlock(Block block);

    /**
     * 修改地块信息
     * @param block block
     * @return message
     */
    Message modifyBlock(Block block);

    /**
     * 删除地块信息
     * @param block block
     * @return message
     */
    Message removeBlock(Block block);
}
