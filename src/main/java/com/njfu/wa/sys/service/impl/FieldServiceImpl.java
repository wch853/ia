package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.mapper.FieldMapper;
import com.njfu.wa.sys.service.FieldService;
import com.njfu.wa.sys.util.Message;
import com.njfu.wa.sys.util.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private MessageFactory messageFactory;

    /**
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
    public Message addField(Field field, Block block, Crop crop) {
        this.convertNull(field, crop);

        field.setBlock(block);

        int res = fieldMapper.insertField(field);
        if (res == 0) {
            return messageFactory.failMessage("新增大棚信息失败，请检查新增编号是否存在！");
        }

        return messageFactory.successMessage("新增大棚信息成功！");
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
    public Message modifyField(Field field, Block block, Crop crop) {
        this.convertNull(field, crop);

        field.setBlock(block);

        int res = fieldMapper.updateField(field);
        if (res == 0) {
            return messageFactory.failMessage("修改大棚信息失败！");
        }

        return messageFactory.successMessage("修改大棚信息成功！");
    }

    /**
     * 删除大棚信息
     *
     * @param field field
     * @return message
     */
    @Override
    public Message removeField(Field field) {
        int res = fieldMapper.deleteField(field);

        if (res == 0) {
            return messageFactory.failMessage("删除大棚信息失败！");
        }

        return messageFactory.successMessage("删除大棚信息成功！");
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
