package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.FieldStatus;
import com.njfu.wa.sys.domain.WarnThreshold;
import com.njfu.wa.sys.enums.UseStatusEnum;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.mapper.FieldStatusMapper;
import com.njfu.wa.sys.mapper.WarnThresholdMapper;
import com.njfu.wa.sys.service.WarnThresholdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WarnThresholdServiceImpl implements WarnThresholdService {

    @Resource
    private WarnThresholdMapper warnThresholdMapper;

    @Resource
    private FieldStatusMapper fieldStatusMapper;

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
     */
    @Override
    @Transactional
    public void modifyWarnThreshold(WarnThreshold warnThreshold) throws BusinessException {
        int res = warnThresholdMapper.updateWarnThreshold(warnThreshold);
        if (res <= 0) {
            throw new BusinessException("更新阈值失败！");
        }
        // 将阈值置为不使用后，将大棚数据项中所有该项数据置空
        if (UseStatusEnum.UNUSE.getCode().equals(warnThreshold.getUseStatus())) {
            fieldStatusMapper.updateFieldStatus(warnThreshold.getThresholdType());
        }
    }
}
