package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Field;

import java.util.List;

public interface FieldService {

    List<Field> getFields(String fieldName, String blockId, String cropId);
}
