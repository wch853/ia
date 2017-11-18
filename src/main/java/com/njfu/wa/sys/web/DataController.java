package com.njfu.wa.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据管理控制器
 */
@Controller
@RequestMapping("/sys/data")
public class DataController {

    /**
     * 数据查询页面
     *
     * @return page
     */
    @GetMapping("/query")
    public String dataQuery() {
        return "sys/data/dataQuery";
    }

    /**
     * 数据分析页面
     *
     * @return page
     */
    @GetMapping("/analysis")
    public String dataAnalysis() {
        return "sys/data/dataAnalysis";
    }
}
