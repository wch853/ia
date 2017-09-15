package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.util.Message;
import com.njfu.wa.sys.domain.util.MessageFactory;
import com.njfu.wa.sys.mapper.FieldStatusMapper;
import com.njfu.wa.sys.service.FieldStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldStatusServiceImpl implements FieldStatusService {

    @Autowired
    private FieldStatusMapper fieldStatusMapper;

    @Autowired
    private MessageFactory<com.njfu.wa.sys.domain.FieldStatus> messageFactory;

    @Override
    public Message getFieldStatusById(String fieldId) {
        return messageFactory.dataMessage(fieldStatusMapper.selectFieldStatus(fieldId).get(0));
    }
}
