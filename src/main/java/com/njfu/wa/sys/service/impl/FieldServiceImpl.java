package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.Enums.MessageEnum;
import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.mapper.FieldMapper;
import com.njfu.wa.sys.service.FieldService;
import com.njfu.wa.sys.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldMapper fieldMapper;

    @Override
    public List<Field> getFields(Field field, String blockId, String cropId) {
        field.setBlock(new Block(blockId));
        field.setCrop(new Crop(cropId));
        return fieldMapper.selectFields(field);
    }

    @Override
    public Message addField(Field field, String blockId, String cropId) {
        field.setBlock(new Block(blockId));
        if (cropId != "") {
            field.setCrop(new Crop(cropId));
        }

        int rowCount = fieldMapper.insertField(field);
        if (rowCount == 0) {
            return new Message(MessageEnum.FAIL.getCode(), "新增大棚信息失败！");
        }

        return new Message(MessageEnum.SUCCESS.getCode(), "新增大棚信息成功！");
    }

    @Override
    public Message modifyField(Field field, String blockId, String cropId) {
        field.setBlock(new Block(blockId));
        if (cropId != "") {
            field.setCrop(new Crop(cropId));
        }

        int rowCount = fieldMapper.updateField(field);
        if (rowCount == 0) {
            return new Message(MessageEnum.FAIL.getCode(), "修改大棚信息失败！");
        }

        return new Message(MessageEnum.SUCCESS.getCode(), "修改大棚信息成功！");
    }

    @Override
    public Message removeField(String fieldId) {
        int rowCount = fieldMapper.deleteField(fieldId);

        if (rowCount == 0) {
            return new Message(MessageEnum.FAIL.getCode(), "删除大棚信息失败！");
        }

        return new Message(MessageEnum.SUCCESS.getCode(), "删除大棚信息成功！");
    }
}
