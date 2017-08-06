package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Block;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlockMapper {

    // 查询地块档案
    List<Block> selectBlocks();

    // 查询所有地块档案及对应地块下使用状态的大棚
    List<Block> selectBlocksAndFields();
}
