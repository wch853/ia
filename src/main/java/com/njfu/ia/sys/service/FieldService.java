package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Field;

import java.util.List;

public interface FieldService {

    /**
     * 查询大棚信息
     *
     * @param field
     * @return
     */
    List<Field> queryFields(Field field);

    /**
     * 新增大棚信息
     *
     * @param field
     */
    void addField(Field field);

    /**
     * 修改大棚信息
     *
     * @param field
     */
    void modifyField(Field field);

    /**
     * 删除大棚信息
     *
     * @param id
     */
    void removeField(Integer id);
}
