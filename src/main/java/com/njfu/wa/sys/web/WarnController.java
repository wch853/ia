package com.njfu.wa.sys.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njfu.wa.sys.domain.WarnThreshold;
import com.njfu.wa.sys.domain.util.PaginationResult;
import com.njfu.wa.sys.domain.util.Result;
import com.njfu.wa.sys.service.WarnThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("sys/warn")
public class WarnController {

    @Autowired
    private WarnThresholdService warnThresholdService;

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
        System.out.println(warnThreshold);
        return warnThresholdService.modifyWarnThreshold(warnThreshold);
    }

    /**
     * 报警记录页面
     *
     * @return page
     */
    @RequestMapping("/record")
    public String record() {
        return "sys/warn/record";
    }

}
