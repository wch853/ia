package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Block;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlockMapper {

    /**
     * 查询地块信息
     *
     * @param block
     * @return
     */
    List<Block> selectBlocks(Block block);

    /**
     * 查询所有地块信息及相应地块下使用状态的大棚
     *
     * @return
     */
    List<Block> selectBlocksAndFields();

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
     * @param block
     * @return
     */
    int deleteBlock(Block block);
}
