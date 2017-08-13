package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.util.Message;

import java.util.List;

public interface FieldService {

    List<Field> getFields(Field field, String blockId, String cropId);

    Message addField(Field field, String blockId, String cropId);

    Message modifyField(Field field, String blockId, String cropId);

    Message removeField(String fieldId);
}
