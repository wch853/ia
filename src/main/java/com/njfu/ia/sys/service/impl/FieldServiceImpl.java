package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.Field;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.FieldMapper;
import com.njfu.ia.sys.mapper.FieldStatusMapper;
import com.njfu.ia.sys.mapper.SensorMapper;
import com.njfu.ia.sys.service.FieldService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    @Resource
    private FieldMapper fieldMapper;

    @Resource
    private FieldStatusMapper fieldStatusMapper;

    @Resource
    private SensorMapper sensorMapper;

    /**
     * 获取大棚列表
     *
     * @param field
     * @return
     */
    @Override
    public List<Field> getFields(Field field) {
        return fieldMapper.selectFields(field);
    }

    /**
     * 新增大棚信息
     *
     * @param field
     */
    @Override
    public void addField(Field field) {
        int addField = fieldMapper.insertField(field);
        if (addField <= 0) {
            throw new BusinessException("新增大棚信息失败！");
        }
    }

    /**
     * 修改大棚信息
     *
     * @param field
     */
    @Override
    public void modifyField(Field field) {
        int res = fieldMapper.updateField(field);
        if (res <= 0) {
            throw new BusinessException("修改大棚信息失败！");
        }
    }

    /**
     * 删除大棚信息
     *
     * @param id
     */
    @Override
    public void removeField(Integer id) {
        int delField = fieldMapper.deleteField(id);
        if (delField <= 0) {
            throw new BusinessException("删除大棚信息失败！");
        }
    }
}
