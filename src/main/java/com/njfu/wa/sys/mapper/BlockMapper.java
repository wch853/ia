package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Block;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlockMapper {

    List<Block> selectBlocks();
}
