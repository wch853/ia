package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.FieldStatus;

public interface FieldStatusService {

    /**
     * 根据大棚编号获取大棚当前状态
     *
     * @param fieldId fieldId
     * @return Result
     */
    FieldStatus getFieldStatusById(String fieldId);

}
