package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.DataType;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.DataTypeMapper;
import com.njfu.ia.sys.service.DataTypeService;
import com.njfu.ia.sys.utils.Constants;
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
     * @param dataType
     * @return
     */
    @Override
    public List<DataType> queryDataTypes(DataType dataType) {
        return dataTypeMapper.selectDataTypes(dataType);
    }

    /**
     * 新增数据类型
     *
     * @param dataType
     */
    @Override
    public void addDataType(DataType dataType) {
        // 默认监控中状态
        dataType.setUseStatus(Constants.IN_USE_STATUS);
        int count = dataTypeMapper.insertDataType(dataType);
        if (count <= 0) {
            throw new RuntimeException("新增数据类型失败！");
        }
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

    /**
     * 删除数据类型
     *
     * @param id
     */
    @Override
    public void reomveDataType(Integer id) {
        int count = dataTypeMapper.deleteDataType(id);
        if (count <= 0) {
            throw new RuntimeException("删除数据类型失败");
        }
    }

}
