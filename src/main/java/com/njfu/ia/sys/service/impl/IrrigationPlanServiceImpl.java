package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.IrrigationPlan;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.IrrigationPlanMapper;
import com.njfu.ia.sys.service.IrrigationPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IrrigationPlanServiceImpl implements IrrigationPlanService {

    @Resource
    private IrrigationPlanMapper irrigationPlanMapper;

    /**
     * 获取排灌方案列表
     *
     * @param irrigationPlan irrigationPlan
     * @return IrrigationPlan list
     */
    @Override
    public List<IrrigationPlan> getIrrigationPlans(IrrigationPlan irrigationPlan) {
        return irrigationPlanMapper.selectIrrigationPlans(irrigationPlan);
    }

    /**
     * 新增排灌方案
     *
     * @param irrigationPlan irrigationPlan
     */
    @Override
    public void addIrrigationPlan(IrrigationPlan irrigationPlan) {
        this.convertNull(irrigationPlan);
        int count = irrigationPlanMapper.insertIrrigationPlan(irrigationPlan);
        if (count <= 0) {
            throw new BusinessException("新增排灌方案失败！");
        }
    }

    /**
     * 修改排灌方案
     *
     * @param irrigationPlan irrigationPlan
     */
    @Override
    public void modifyIrrigationPlan(IrrigationPlan irrigationPlan) {
        this.convertNull(irrigationPlan);
        int count = irrigationPlanMapper.updateIrrigationPlan(irrigationPlan);
        if (count <= 0) {
            throw new BusinessException("修改排灌方案失败！");
        }
    }

    /**
     * 删除排灌方案
     *
     * @param id id
     */
    @Override
    public void removeIrrigationPlan(Integer id) {
        int count = irrigationPlanMapper.deleteIrrigationPlan(id);
        if (count <= 0) {
            throw new BusinessException("删除排灌方案失败！");
        }
    }

    /**
     * 将planPs由空字符串转为null
     *
     * @param irrigationPlan planPs
     */
    private void convertNull(IrrigationPlan irrigationPlan) {
        if ("".equals(irrigationPlan.getPlanPs())) {
            irrigationPlan.setPlanPs(null);
        }
    }
}
