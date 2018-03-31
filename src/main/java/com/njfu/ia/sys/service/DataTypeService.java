package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.DataType;

import java.util.List;

public interface DataTypeService {

    /**
     * 查询数据类型
     *
     * @param dataType dataType
     * @return DataType list
     */
    List<DataType> getDataTypes(DataType dataType);

    /**
     * 更新数据类型
     *
     * @param dataType dataType
     */
    void updateDataType(DataType dataType);
}
