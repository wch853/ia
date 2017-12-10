package com.njfu.wa.sys.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njfu.wa.sys.domain.*;
import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.service.FieldService;
import com.njfu.wa.sys.service.WarnRecordService;
import com.njfu.wa.sys.service.WarnThresholdService;
import com.njfu.wa.sys.utils.PaginationResult;
import com.njfu.wa.sys.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("sys/warn")
public class WarnController {

    @Resource
    private WarnThresholdService warnThresholdService;

    @Resource
    private WarnRecordService warnRecordService;

    @Resource
    private FieldService fieldService;

    private static final Logger LOGGER = LoggerFactory.getLogger(WarnController.class);

    /**
     * 阈值设置页面
     *
     * @return Page
     */
    @GetMapping("/threshold")
    public String threshold() {
        return "sys/warn/threshold";
    }

    /**
     * 获取阈值列表
     *
     * @param offset        offset
     * @param limit         limit
     * @param warnThreshold thresholdType
     * @return json data
     */
    @GetMapping("/threshold/data")
    public @ResponseBody
    PaginationResult getWarnThreshold(int offset, int limit, WarnThreshold warnThreshold) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<WarnThreshold> warnThresholds = warnThresholdService.getWarnThreshold(warnThreshold);
        return new PaginationResult<>(page.getTotal(), warnThresholds);
    }

    /**
     * 修改阈值信息
     *
     * @param warnThreshold ceil floor useStatus
     * @return message
     */
    @PostMapping("/threshold/modify")
    public @ResponseBody
    Result modifyWarnThreshold(WarnThreshold warnThreshold) {
        try {
            warnThresholdService.modifyWarnThreshold(warnThreshold);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 报警记录页面
     *
     * @return Page
     */
    @GetMapping("/record")
    public String record(Model model) {
        List<Field> fields = fieldService.getFields(new Field(), new Block(), new Crop());
        model.addAttribute("fields", fields);
        return "sys/warn/record";
    }

    /**
     * 获取报警记录列表
     *
     * @param limit      limit
     * @param offset     offset
     * @param warnRecord fieldId warnType warnTime flag
     * @param start      start
     * @param end        end
     * @return json data
     */
    @GetMapping("/record/data")
    public @ResponseBody
    PaginationResult getWarnRecord(int limit, int offset, WarnRecord warnRecord, String start, String end) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<WarnRecord> warnRecords = warnRecordService.getWarnRecord(warnRecord, start, end);
        return new PaginationResult<>(page.getTotal(), warnRecords);
    }

    /**
     * 报警详情页面
     *
     * @return Page
     */
    @GetMapping("/detail")
    public String detail() {
        return "sys/warn/detail";
    }

    /**
     * 获取未处理报警数据
     *
     * @return json data
     */
    @GetMapping("/record/unhandle")
    public @ResponseBody
    Result getUnHandleRecord() {
        List<WarnRecord> unHandleWarnRecords = warnRecordService.getUnHandleWarnRecord();
        return Result.response(ResultEnum.DATA, unHandleWarnRecords);
    }

    /**
     * 修改报警记录处理标志
     *
     * @param ids  id
     * @param flag flag
     * @return json Result
     */
    @PostMapping("/record/modify")
    public @ResponseBody
    Result modifyWarnRecord(@RequestParam("ids[]") Integer[] ids, String flag) {
        try {
            warnRecordService.modifyWarnRecord(ids, flag);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 获取未处理报警记录数量
     *
     * @return json data
     */
    @GetMapping("/record/unhandle/count")
    public @ResponseBody
    Result getUnhandleRecordCount() {
        int count = warnRecordService.getUnhandleRecordCount();
        return Result.response(ResultEnum.WARN, count);
    }
}
