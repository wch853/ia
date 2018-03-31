package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Field;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FieldMapper {

    /**
     * 获取大棚列表
     *
     * @param field
     * @return
     */
    List<Field> selectFields(Field field);

    /**
     * 新增大棚信息
     *
     * @param field
     * @return
     */
    int insertField(Field field);

    /**
     * 修改大棚信息
     *
     * @param field
     * @return
     */
    int updateField(Field field);

    /**
     * 删除大棚信息
     *
     * @param id
     * @return
     */
    int deleteField(@Param("id") Integer id);
}
