package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Block;

import java.util.List;

public interface BlockService {

    /**
     * 获取地块信息
     *
     * @param block
     * @return
     */
    List<Block> queryBlocks(Block block);

    /**
     * index页获取所有地块信息及相应地块下使用状态的大棚
     *
     * @return
     */
    List<Block> queryBlocksWithSections();

    /**
     * 新增地块信息
     *
     * @param block
     */
    void addBlock(Block block);

    /**
     * 修改地块信息
     *
     * @param block
     */
    void modifyBlock(Block block);

    /**
     * 删除地块信息
     *
     * @param id
     */
    void removeBlock(Integer id);
}