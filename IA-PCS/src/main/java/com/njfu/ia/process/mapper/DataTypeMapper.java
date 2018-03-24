package com.njfu.ia.process.mapper;

import com.njfu.ia.process.domain.DataType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataTypeMapper {

    /**
     * 查询所有数据类型
     *
     * @param dataType dataType
     * @return return
     */
    List<DataType> selectDataTypes(DataType dataType);
}
