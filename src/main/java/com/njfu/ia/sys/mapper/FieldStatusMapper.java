package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Field;
import com.njfu.ia.sys.domain.FieldStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
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
     * @param fieldId fieldId
     * @return FieldStatus
     */
    FieldStatus selectFieldStatus(@Param("fieldId") String fieldId);

    /**
     * 删除大棚数据项
     *
     * @param field fieldId
     * @return row count
     */
    int deleteFieldStatus(Field field);

    /**
     * 将指定阈值类型的大棚数据置空
     *
     * @param thresholdType thresholdType
     */
    void updateFieldStatus(@Param("thresholdType") String thresholdType);
}
