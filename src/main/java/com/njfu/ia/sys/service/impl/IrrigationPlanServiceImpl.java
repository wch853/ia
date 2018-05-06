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
     * 获取灌溉方案列表
     *
     * @param irrigationPlan
     * @return
     */
    @Override
    public List<IrrigationPlan> queryIrrigationPlans(IrrigationPlan irrigationPlan) {
        return irrigationPlanMapper.selectIrrigationPlans(irrigationPlan);
    }

    /**
     * 新增灌溉方案
     *
     * @param irrigationPlan
     */
    @Override
    public void addIrrigationPlan(IrrigationPlan irrigationPlan) {
        int count = irrigationPlanMapper.insertIrrigationPlan(irrigationPlan);
        if (count <= 0) {
            throw new BusinessException("新增灌溉方案失败！");
        }
    }

    /**
     * 修改灌溉方案
     *
     * @param irrigationPlan
     */
    @Override
    public void modifyIrrigationPlan(IrrigationPlan irrigationPlan) {
        int count = irrigationPlanMapper.updateIrrigationPlan(irrigationPlan);
        if (count <= 0) {
            throw new BusinessException("修改灌溉方案失败！");
        }
    }

    /**
     * 删除灌溉方案
     *
     * @param id
     */
    @Override
    public void removeIrrigationPlan(Integer id) {
        int count = irrigationPlanMapper.deleteIrrigationPlan(id);
        if (count <= 0) {
            throw new BusinessException("删除灌溉方案失败！");
        }
    }
}
