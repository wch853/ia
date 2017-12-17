package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.IrrigationPlan;

import java.util.List;

public interface IrrigationPlanService {

    /**
     * 获取排灌方案列表
     *
     * @param irrigationPlan irrigationPlan
     * @return IrrigationPlan list
     */
    List<IrrigationPlan> getIrrigationPlans(IrrigationPlan irrigationPlan);

    /**
     * 新增排灌方案
     *
     * @param irrigationPlan irrigationPlan
     */
    void addIrrigationPlan(IrrigationPlan irrigationPlan);

    /**
     * 修改排灌方案
     *
     * @param irrigationPlan irrigationPlan
     */
    void modifyIrrigationPlan(IrrigationPlan irrigationPlan);

    /**
     * 删除排灌方案
     *
     * @param id id
     */
    void removeIrrigationPlan(Integer id);
}
