package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Block;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlockMapper {

    /**
     * 查询地块信息
     *
     * @param block blockId blockName
     * @return data
     */
    List<Block> selectBlocks(Block block);

    /**
     * 查询所有地块信息及相应地块下使用状态的大棚
     *
     * @return data
     */
    List<Block> selectBlocksAndFields();

    /**
     * 新增地块信息
     *
     * @param block blockId blockName blockLoc blockPs
     * @return row count
     */
    int insertBlock(Block block);

    /**
     * 修改地块信息
     *
     * @param block blockId blockName blockLoc blockPs
     * @return row count
     */
    int updateBlock(Block block);

    /**
     * 删除地块信息
     *
     * @param block blockId
     * @return row count
     */
    int deleteBlock(Block block);
}
