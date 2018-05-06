package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Block;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlockMapper {

    /**
     * 查询地块信息
     *
     * @param block
     * @return
     */
    List<Block> selectBlocks(Block block);

    /**
     * 查询所有地块信息及相应地块下使用状态的区块
     *
     * @return
     */
    List<Block> selectBlocksWithSections();

    /**
     * 新增地块信息
     *
     * @param block
     * @return
     */
    int insertBlock(Block block);

    /**
     * 修改地块信息
     *
     * @param block
     * @return
     */
    int updateBlock(Block block);

    /**
     * 删除地块信息
     *
     * @param id
     * @return
     */
    int deleteBlock(@Param("id") Integer id);
}
