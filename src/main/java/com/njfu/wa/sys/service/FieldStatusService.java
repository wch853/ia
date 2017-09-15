package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.util.Message;

public interface FieldStatusService {

    // 根据大棚编号获取大棚当前状态
    Message getFieldStatusById(String fieldId);

}
