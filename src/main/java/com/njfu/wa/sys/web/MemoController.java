package com.njfu.wa.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 记录管理控制器
 */
@Controller
@RequestMapping("sys/memo")
public class MemoController {

    @RequestMapping
    public String memo() {
        return "sys/memo/log";
    }
}
