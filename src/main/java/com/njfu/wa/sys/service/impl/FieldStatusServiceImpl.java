package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.mapper.FieldStatusMapper;
import com.njfu.wa.sys.service.FieldStatusService;
import com.njfu.wa.sys.utils.Result;
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
    public Result getFieldStatusById(String fieldId) {
        return Result.data(fieldStatusMapper.selectFieldStatus(fieldId));
    }
}
