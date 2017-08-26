package com.njfu.wa.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据管理控制器
 */
@Controller
@RequestMapping("/sys/data")
public class DataController {

    @RequestMapping("/query")
    public String dataQuery() {
        return "sys/data/dataQuery";
    }

    @RequestMapping("/analysis")
    public String dataAnalysis() {
        return "sys/data/dataAnalysis";
    }
}
