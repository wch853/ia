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
    public List<Field> getFields(String fieldName, String blockId, String cropId, String useStatus) {
        Field field = new Field();
        field.setFieldName(fieldName);
        field.setBlock(new Block(blockId));
        field.setCrop(new Crop(cropId));
        field.setUseStatus(useStatus);
        return fieldMapper.selectFields(field);
    }

    @Override
    public Message addField(String fieldId, String fieldName, String blockId, String cropId, String useStatus, String fieldPs) {
        Field field = new Field();
        field.setFieldId(fieldId.trim());
        field.setFieldName(fieldName.trim());
        field.setBlock(new Block(blockId));
        if (cropId != "") {
            field.setCrop(new Crop(cropId));
        }
        field.setUseStatus(useStatus);
        if (fieldPs.trim() != "") {
            field.setFieldPs(fieldPs);
        }

        int rowCount = fieldMapper.insertField(field);
        if (rowCount == 0) {
            return new Message(MessageEnum.FAIL.getCode(), "新增大棚信息失败！");
        }

        return new Message(MessageEnum.SUCCESS.getCode(), "新增大棚信息成功！");
    }

    @Override
    public Message modifyField(String fieldId, String fieldName, String blockId, String cropId, String useStatus, String fieldPs) {
        Field field = new Field();
        field.setFieldId(fieldId);
        field.setFieldName(fieldName.trim());
        field.setBlock(new Block(blockId));
        if (cropId != "") {
            field.setCrop(new Crop(cropId));
        }
        field.setUseStatus(useStatus);
        if (fieldPs.trim() != "") {
            field.setFieldPs(fieldPs);
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
