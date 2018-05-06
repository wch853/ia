package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.IrrigationPlan;

import java.util.List;

public interface IrrigationPlanService {

    /**
     * 获取灌溉方案列表
     *
     * @param irrigationPlan irrigationPlan
     * @return IrrigationPlan list
     */
    List<IrrigationPlan> queryIrrigationPlans(IrrigationPlan irrigationPlan);

    /**
     * 新增灌溉方案
     *
     * @param irrigationPlan irrigationPlan
     */
    void addIrrigationPlan(IrrigationPlan irrigationPlan);

    /**
     * 修改灌溉方案
     *
     * @param irrigationPlan irrigationPlan
     */
    void modifyIrrigationPlan(IrrigationPlan irrigationPlan);

    /**
     * 删除灌溉方案
     *
     * @param id id
     */
    void removeIrrigationPlan(Integer id);
}
