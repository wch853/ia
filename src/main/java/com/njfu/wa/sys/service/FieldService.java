package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.util.Message;

import java.util.List;

public interface FieldService {

    List<Field> getFields(String fieldName, String blockId, String cropId, String useStatus);

    Message addField(String fieldId, String fieldName, String blockId, String cropId, String useStatus, String fieldPs);

    Message modifyField(String fieldId, String fieldName, String blockId, String cropId, String useStatus, String fieldPs);

    Message removeField(String fieldId);
}
