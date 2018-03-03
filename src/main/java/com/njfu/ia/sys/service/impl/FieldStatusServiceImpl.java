package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.FieldStatus;
import com.njfu.ia.sys.mapper.FieldStatusMapper;
import com.njfu.ia.sys.service.FieldStatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FieldStatusServiceImpl implements FieldStatusService {

    @Resource
    private FieldStatusMapper fieldStatusMapper;

    /**
     * 根据大棚编号获取大棚当前状态
     *
     * @param fieldId fieldId
     * @return Result
     */
    @Override
    public FieldStatus getFieldStatusById(String fieldId) {
        return fieldStatusMapper.selectFieldStatus(fieldId);
    }
}
