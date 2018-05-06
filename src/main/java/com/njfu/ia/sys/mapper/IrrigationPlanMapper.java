package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.IrrigationPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IrrigationPlanMapper {

    /**
     * 获取灌溉方案列表
     *
     * @param irrigationPlan
     * @return
     */
    List<IrrigationPlan> selectIrrigationPlans(IrrigationPlan irrigationPlan);

    /**
     * 新增灌溉方案
     *
     * @param irrigationPlan
     * @return
     */
    int insertIrrigationPlan(IrrigationPlan irrigationPlan);

    /**
     * 更新灌溉方案
     *
     * @param irrigationPlan
     * @return
     */
    int updateIrrigationPlan(IrrigationPlan irrigationPlan);

    /**
     * 删除灌溉方案
     *
     * @param id
     * @return
     */
    int deleteIrrigationPlan(@Param("id") Integer id);
}
