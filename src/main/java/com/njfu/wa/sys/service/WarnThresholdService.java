package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.WarnThreshold;
import com.njfu.wa.sys.domain.util.Result;

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
     * @return message
     */
    Result modifyWarnThreshold(WarnThreshold warnThreshold);
}
