package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Field;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FieldMapper {

    List<Field> selectFields(Field field);

    // TODO 新增修改删除
}
