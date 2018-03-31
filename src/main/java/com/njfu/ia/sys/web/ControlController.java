package com.njfu.ia.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.ia.sys.domain.IrrigationPlan;
import com.njfu.ia.sys.enums.ResultEnum;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.service.FieldService;
import com.njfu.ia.sys.service.IrrigationPlanService;
import com.njfu.ia.sys.utils.Constants;
import com.njfu.ia.sys.utils.PaginationResult;
import com.njfu.ia.sys.utils.Result;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 控制管理
 */
@Controller
@RequestMapping("/sys/control")
public class ControlController {

    @Resource
    private IrrigationPlanService irrigationPlanService;

    @Resource
    private FieldService fieldService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ControlController.class);

    /**
     * 排灌管理页
     *
     * @return
     */
    @GetMapping("/plan")
    public String plan() {
        return "sys/control/plan";
    }

    /**
     * 获取排灌方案列表
     *
     * @param offset         offset
     * @param limit          limit
     * @param irrigationPlan irrigationPlan
     * @return json data list
     */
    @GetMapping("/plan/data")
    public @ResponseBody
    PaginationResult getIrrigationPlans(int offset, int limit, IrrigationPlan irrigationPlan) {
        PageHelper.offsetPage(offset, limit);
        List<IrrigationPlan> irrigationPlans = irrigationPlanService.getIrrigationPlans(irrigationPlan);
        PageInfo<IrrigationPlan> page = new PageInfo<>(irrigationPlans);
        return new PaginationResult<>(page.getTotal(), irrigationPlans);
    }

    /**
     * 新增排灌方案
     *
     * @param irrigationPlan irrigationPlan
     * @return json Result
     */
    @PostMapping("/plan/add")
    public @ResponseBody
    Result addIrrigationPlan(IrrigationPlan irrigationPlan) {
        try {
            irrigationPlanService.addIrrigationPlan(irrigationPlan);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改排灌方案
     *
     * @param irrigationPlan irrigationPlan
     * @return json Result
     */
    @PostMapping("/plan/modify")
    public @ResponseBody
    Result modifyIrrigationPlan(IrrigationPlan irrigationPlan) {
        try {
            irrigationPlanService.modifyIrrigationPlan(irrigationPlan);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除排灌方案
     *
     * @param id id
     * @return json Result
     */
    @PostMapping("/plan/remove")
    public @ResponseBody
    Result removeIrrigationPlan(Integer id) {
        try {
            irrigationPlanService.removeIrrigationPlan(id);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 执行排灌方案
     *
     * @param planId planId
     * @param start  start time
     * @return json Result
     */
    @PostMapping("/plan/execute")
    public @ResponseBody
    Result doExecute(String planId, String start) {
        try {
            // TODO do execute
            Date date = DateUtils.parseDate(start, Constants.DATE_MINUTE_FORMAT);
            if (date.compareTo(new Date()) < 0) {
                throw new BusinessException("执行时间必须大于当前时间！");
            }
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }
}
