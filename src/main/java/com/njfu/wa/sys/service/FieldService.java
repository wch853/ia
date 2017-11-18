package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.domain.Field;

import java.util.List;

public interface FieldService {

    /**
     * @param field fieldName useStatus
     * @param block blockId
     * @param crop  cropId
     * @return data
     */
    List<Field> getFields(Field field, Block block, Crop crop);

    /**
     * 新增大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs
     * @param block blockId
     * @param crop  cropId
     */
    void addField(Field field, Block block, Crop crop);

    /**
     * 修改大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs
     * @param block blockId
     * @param crop  cropId
     */
    void modifyField(Field field, Block block, Crop crop);

    /**
     * 删除大棚信息
     *
     * @param field field
     */
    void removeField(Field field);
}
