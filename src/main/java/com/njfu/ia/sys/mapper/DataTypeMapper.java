package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.DataType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataTypeMapper {

    /**
     * 查询数据类型
     *
     * @param dataType
     * @return
     */
    List<DataType> selectDataTypes(DataType dataType);

    /**
     * 新增数据类型
     *
     * @param dataType
     * @return
     */
    int insertDataType(DataType dataType);

    /**
     * 更新数据类型
     *
     * @param dataType
     * @return
     */
    int updateDataType(DataType dataType);

    /**
     * 删除数据类型
     *
     * @param id
     * @return
     */
    int deleteDataType(Integer id);

}
