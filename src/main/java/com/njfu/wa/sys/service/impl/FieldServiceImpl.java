package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.mapper.FieldMapper;
import com.njfu.wa.sys.mapper.FieldStatusMapper;
import com.njfu.wa.sys.mapper.SensorMapper;
import com.njfu.wa.sys.service.FieldService;
import com.njfu.wa.sys.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @return message
     */
    @Override
    @Transactional
    public Result addField(Field field, Block block, Crop crop) {
        this.convertNull(field, crop);

        field.setBlock(block);

        int res = fieldMapper.insertField(field);
        if (res == 0) {
            return Result.fail("新增大棚信息失败，请检查新增编号是否存在！");
        }

        // 新增大棚状态数据项
        fieldStatusMapper.insertFieldStatus(field);

        return Result.success("新增大棚信息成功！");
    }

    /**
     * 修改大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs
     * @param block blockId
     * @param crop  cropId
     * @return message
     */
    @Override
    public Result modifyField(Field field, Block block, Crop crop) {
        this.convertNull(field, crop);

        field.setBlock(block);
        int res = fieldMapper.updateField(field);
        if (res == 0) {
            return Result.fail("修改大棚信息失败！");
        }

        return Result.success("修改大棚信息成功！");
    }

    /**
     * 删除大棚信息
     *
     * @param field field
     * @return message
     */
    @Override
    @Transactional
    public Result removeField(Field field) {
        int res = fieldMapper.deleteField(field);

        if (res == 0) {
            return Result.fail("删除大棚信息失败！");
        }

        // 删除大棚状态数据项
        fieldStatusMapper.deleteFieldStatus(field);
        // 将该大棚下的传感器所属大棚置空
        sensorMapper.updateSensorField(field.getFieldId());
        return Result.success("删除大棚信息成功！");
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
