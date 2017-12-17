package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.IrrigationPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IrrigationPlanMapper {

    /**
     * 获取排灌方案列表
     *
     * @param irrigationPlan irrigationPlan
     * @return IrrigationPlan list
     */
    List<IrrigationPlan> selectIrrigationPlans(IrrigationPlan irrigationPlan);

    /**
     * 新增排灌方案
     *
     * @param irrigationPlan irrigationPlan
     * @return count
     */
    int insertIrrigationPlan(IrrigationPlan irrigationPlan);

    /**
     * 更新排灌方案
     *
     * @param irrigationPlan irrigationPlan
     * @return count
     */
    int updateIrrigationPlan(IrrigationPlan irrigationPlan);

    /**
     * 删除排灌方案
     *
     * @param id id
     * @return count
     */
    int deleteIrrigationPlan(@Param("id") Integer id);
}
