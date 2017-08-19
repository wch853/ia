package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Field;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FieldMapper {

    List<Field> selectFields(Field field);

    int insertField(Field field);

    int updateField(Field field);

    int deleteField(@Param("fieldId") String fieldId);
}
