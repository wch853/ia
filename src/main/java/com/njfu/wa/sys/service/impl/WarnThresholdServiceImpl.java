package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.WarnThreshold;
import com.njfu.wa.sys.domain.util.Result;
import com.njfu.wa.sys.domain.util.ResultFactory;
import com.njfu.wa.sys.mapper.WarnThresholdMapper;
import com.njfu.wa.sys.service.WarnThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarnThresholdServiceImpl implements WarnThresholdService {

    @Autowired
    private WarnThresholdMapper warnThresholdMapper;

    @Autowired
    private ResultFactory resultFactory;

    /**
     * 获取阈值信息
     *
     * @param warnThreshold thresholdType
     * @return data
     */
    @Override
    public List<WarnThreshold> getWarnThreshold(WarnThreshold warnThreshold) {
        return warnThresholdMapper.selectWarnThreshold(warnThreshold);
    }

    /**
     * 修改阈值信息
     *
     * @param warnThreshold ceil floor useStatus
     * @return message
     */
    @Override
    public Result modifyWarnThreshold(WarnThreshold warnThreshold) {

        int res = warnThresholdMapper.updateWarnThreshold(warnThreshold);

        if (res == 0) {
            return resultFactory.failMessage("更新阈值失败！");
        }

        return resultFactory.successMessage("更新阈值成功！");
    }
}
