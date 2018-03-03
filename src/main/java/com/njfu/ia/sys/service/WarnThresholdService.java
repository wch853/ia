package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.WarnThreshold;
import com.njfu.ia.sys.exception.BusinessException;

import java.util.List;

public interface WarnThresholdService {

    /**
     * 获取阈值信息
     *
     * @param warnThreshold thresholdType
     * @return data
     */
    List<WarnThreshold> getWarnThreshold(WarnThreshold warnThreshold);

    /**
     * 修改阈值信息
     *
     * @param warnThreshold ceil floor useStatus
     * @throws BusinessException BusinessException
     */
    void modifyWarnThreshold(WarnThreshold warnThreshold) throws BusinessException;
}
