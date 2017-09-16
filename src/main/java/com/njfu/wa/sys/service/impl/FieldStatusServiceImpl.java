package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.util.Result;
import com.njfu.wa.sys.domain.util.ResultFactory;
import com.njfu.wa.sys.mapper.FieldStatusMapper;
import com.njfu.wa.sys.service.FieldStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldStatusServiceImpl implements FieldStatusService {

    @Autowired
    private FieldStatusMapper fieldStatusMapper;

    @Autowired
    private ResultFactory<com.njfu.wa.sys.domain.FieldStatus> resultFactory;

    @Override
    public Result getFieldStatusById(String fieldId) {
        return resultFactory.dataResult(fieldStatusMapper.selectFieldStatus(fieldId).get(0));
    }
}
