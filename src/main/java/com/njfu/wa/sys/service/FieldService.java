package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.util.Message;

import java.util.List;

public interface FieldService {

    /**
     * 查询大棚信息
     *
     * @param field
     * @param blockId
     * @param cropId
     * @return
     */
    List<Field> getFields(Field field, String blockId, String cropId);

    /**
     * 新增大棚信息
     *
     * @param field
     * @param blockId
     * @param cropId
     * @return
     */
    Message addField(Field field, String blockId, String cropId);

    /**
     * 修改大棚信息
     *
     * @param field
     * @param blockId
     * @param cropId
     * @return
     */
    Message modifyField(Field field, String blockId, String cropId);

    /**
     * 删除大棚信息
     *
     * @param fieldId
     * @return
     */
    Message removeField(String fieldId);
}
