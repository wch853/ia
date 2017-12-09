package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.mapper.FieldMapper;
import com.njfu.wa.sys.mapper.FieldStatusMapper;
import com.njfu.wa.sys.mapper.SensorMapper;
import com.njfu.wa.sys.service.FieldService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
     * @param field fieldName useStatus
     * @param block blockId
     * @param crop  cropId
     * @return data
     */
    @Override
    public List<Field> getFields(Field field, Block block, Crop crop) {
        field.setBlock(block);
        field.setCrop(crop);
        return fieldMapper.selectFields(field);
    }

    /**
     * 新增大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs
     * @param block blockId
     * @param crop  cropId
     */
    @Override
    @Transactional
    public void addField(Field field, Block block, Crop crop) {
        this.convertNull(field, crop);
        field.setBlock(block);
        int addField = fieldMapper.insertField(field);
        if (addField <= 0) {
            throw new BusinessException("新增大棚信息失败！");
        }

        // 新增大棚状态数据项
        int addFieldStatus = fieldStatusMapper.insertFieldStatus(field);
        if (addFieldStatus <= 0) {
            throw new BusinessException("新增大棚数据项信息失败！");
        }
    }

    /**
     * 修改大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs
     * @param block blockId
     * @param crop  cropId
     */
    @Override
    public void modifyField(Field field, Block block, Crop crop) {
        this.convertNull(field, crop);
        field.setBlock(block);
        int res = fieldMapper.updateField(field);
        if (res <= 0) {
            throw new BusinessException("修改大棚信息失败！");
        }
    }

    /**
     * 删除大棚信息
     *
     * @param field field
     */
    @Override
    @Transactional
    public void removeField(Field field) {
        int delField = fieldMapper.deleteField(field);
        if (delField <= 0) {
            throw new BusinessException("删除大棚信息失败！");
        }
        // 删除大棚状态数据项
        int delFieldStatus = fieldStatusMapper.deleteFieldStatus(field);
        if (delFieldStatus <= 0) {
            throw new BusinessException("删除大棚数据项信息失败！");
        }
        // 将该大棚下的传感器所属大棚置空
        String fieldId = field.getFieldId();
        List<String> sensorIds = sensorMapper.selectSensorsByField(fieldId);
        if (!CollectionUtils.isEmpty(sensorIds)) {
            int updSensor = sensorMapper.updateSensorField(fieldId);
            if (updSensor <= 0) {
                throw new BusinessException("传感器所属大棚置空失败！");
            }
        }
    }

    /**
     * 使得fieldPs、cropId不为空字符串
     *
     * @param field fieldPs
     * @param crop  cropId
     */
    private void convertNull(Field field, Crop crop) {
        if ("".equals(field.getFieldPs())) {
            field.setFieldPs(null);
        }

        if (!"".equals(crop.getCropId())) {
            field.setCrop(crop);
        } else {
            field.setCrop(new Crop());
        }
    }
}
