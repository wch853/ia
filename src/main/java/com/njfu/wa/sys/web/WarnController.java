package com.njfu.wa.sys.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njfu.wa.sys.domain.*;
import com.njfu.wa.sys.domain.util.PaginationResult;
import com.njfu.wa.sys.domain.util.Result;
import com.njfu.wa.sys.domain.util.ResultFactory;
import com.njfu.wa.sys.service.FieldService;
import com.njfu.wa.sys.service.WarnRecordService;
import com.njfu.wa.sys.service.WarnThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("sys/warn")
public class WarnController {

    @Autowired
    private WarnThresholdService warnThresholdService;

    @Autowired
    private WarnRecordService warnRecordService;

    @Resource
    private FieldService fieldService;

    @Autowired
    private ResultFactory resultFactory;

    /**
     * 阈值设置页面
     *
     * @return page
     */
    @RequestMapping("/threshold")
    public String threshold() {
        return "sys/warn/thresholdSet";
    }

    /**
     * 获取阈值列表
     *
     * @param offset        offset
     * @param limit         limit
     * @param warnThreshold thresholdType
     * @return json data
     */
    @RequestMapping("/getWarnThreshold")
    public @ResponseBody
    PaginationResult getWarnThreshold(int offset, int limit, WarnThreshold warnThreshold) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<WarnThreshold> warnThresholds = warnThresholdService.getWarnThreshold(warnThreshold);
        return new PaginationResult(page.getTotal(), warnThresholds);
    }

    /**
     * 修改阈值信息
     *
     * @param warnThreshold ceil floor useStatus
     * @return message
     */
    @PostMapping("/modifyWarnThreshold")
    public @ResponseBody
    Result modifyWarnThreshold(WarnThreshold warnThreshold) {
        return warnThresholdService.modifyWarnThreshold(warnThreshold);
    }

    /**
     * 报警记录页面
     *
     * @return page
     */
    @RequestMapping("/record")
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
    @RequestMapping("/getWarnRecord")
    public @ResponseBody
    PaginationResult getWarnRecord(int limit, int offset, WarnRecord warnRecord, String start, String end) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<WarnRecord> warnRecords = warnRecordService.getWarnRecord(warnRecord, start, end);
        return new PaginationResult(page.getTotal(), warnRecords);
    }

    /**
     * 报警详情页面
     *
     * @return page
     */
    @RequestMapping("/detail")
    public String detail() {
        return "sys/warn/detail";
    }

    /**
     * 获取未处理报警数据
     *
     * @return json data
     */
    @RequestMapping("/getUnHandleRecord")
    public @ResponseBody
    Result getUnHandleRecord() {
        return warnRecordService.getUnHandleWarnRecord();
    }

    /**
     * 修改报警记录处理标志
     * @param warnRecord id flag
     * @return json message
     */
    @PostMapping("/modifyWarnRecord")
    public @ResponseBody Result modifyWarnRecord(WarnRecord warnRecord) {
        return warnRecordService.modifyWarnRecord(warnRecord);
    }
}
