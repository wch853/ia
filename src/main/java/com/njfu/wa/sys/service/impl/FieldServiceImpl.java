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

    @Override
    public List<Field> getFields(Field field, String blockId, String cropId) {
        field.setBlock(new Block(blockId));
        field.setCrop(new Crop(cropId));
        return fieldMapper.selectFields(field);
    }

    @Override
    public Message addField(Field field, String blockId, String cropId) {
        if ("".equals(field.getFieldPs())) {
            field.setFieldPs(null);
        }

        field.setBlock(new Block(blockId));
        if (!"".equals(cropId)) {
            field.setCrop(new Crop(cropId));
        }

        int res = fieldMapper.insertField(field);
        if (res == 0) {
            return messageFactory.failMessage("新增大棚信息失败，请检查新增编号是否存在！");
        }

        return messageFactory.successMessage("新增大棚信息成功！");
    }

    @Override
    public Message modifyField(Field field, String blockId, String cropId) {
        if ("".equals(field.getFieldPs())) {
            field.setFieldPs(null);
        }

        field.setBlock(new Block(blockId));
        if (!"".equals(cropId)) {
            field.setCrop(new Crop(cropId));
        }

        int res = fieldMapper.updateField(field);
        if (res == 0) {
            return messageFactory.failMessage("修改大棚信息失败！");
        }

        return messageFactory.successMessage("修改大棚信息成功！");
    }

    @Override
    public Message removeField(String fieldId) {
        int res = fieldMapper.deleteField(fieldId);

        if (res == 0) {
            return messageFactory.failMessage("删除大棚信息失败！");
        }

        return messageFactory.successMessage("删除大棚信息成功！");
    }
}
