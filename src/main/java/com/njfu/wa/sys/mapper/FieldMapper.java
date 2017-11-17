package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Field;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FieldMapper {

    /**
     * 获取大棚列表
     *
     * @param field fieldName useStatus block crop
     * @return data
     */
    List<Field> selectFields(Field field);

    /**
     * 新增大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs block crop
     * @return row count
     */
    int insertField(Field field);

    /**
     * 修改大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs block crop
     * @return row count
     */
    int updateField(Field field);

    /**
     * 删除大棚信息
     *
     * @param field fieldId
     * @return row count
     */
    int deleteField(Field field);
}
