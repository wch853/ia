package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.DataType;

import java.util.List;

public interface DataTypeService {

    /**
     * 查询数据类型
     *
     * @param
     * @return
     */
    List<DataType> queryDataTypes(DataType dataType);

    /**
     * 新增数据类型
     *
     * @param dataType
     */
    void addDataType(DataType dataType);

    /**
     * 更新数据类型
     *
     * @param dataType
     */
    void updateDataType(DataType dataType);

    /**
     * 删除数据类型
     *
     * @param id
     */
    void reomveDataType(Integer id);
}
