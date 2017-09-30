package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.domain.FieldStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FieldStatusMapper {

    /**
     * 新增大棚数据项
     *
     * @param field fieldId
     * @return row count
     */
    int insertFieldStatus(Field field);

    /**
     * 获取当前大棚状态
     *
     * @return data
     */
    List<FieldStatus> selectFieldStatus(@Param("fieldId") String fieldId);

    /**
     * 删除大棚数据项
     *
     * @param field fieldId
     * @return row count
     */
    int deleteFieldStatus(Field field);

}
