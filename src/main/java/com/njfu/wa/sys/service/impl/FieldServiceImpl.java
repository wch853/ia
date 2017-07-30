package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.mapper.FieldMapper;
import com.njfu.wa.sys.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldMapper fieldMapper;


    @Override
    public List<Field> getFields(String fieldName, String blockId, String cropId) {
        Field field = new Field();
        field.setFieldName(fieldName);
        field.setBlock(new Block(blockId));
        field.setCrop(new Crop(cropId));

        return fieldMapper.selectFields(field);
    }
}
