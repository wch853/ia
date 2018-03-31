package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.DataType;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.DataTypeMapper;
import com.njfu.ia.sys.service.DataTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataTypeServiceImpl implements DataTypeService {

    @Resource
    private DataTypeMapper dataTypeMapper;

    /**
     * 查询数据类型
     *
     * @param dataType dataType
     * @return DataType list
     */
    @Override
    public List<DataType> getDataTypes(DataType dataType) {
        return dataTypeMapper.selectDataTypes(dataType);
    }

    /**
     * 更新数据类型
     *
     * @param dataType dataType
     */
    @Override
    public void updateDataType(DataType dataType) {
        int count = dataTypeMapper.updateDataType(dataType);
        if (count <= 0) {
            throw new BusinessException("更新数据类型失败！");
        }
    }

}
