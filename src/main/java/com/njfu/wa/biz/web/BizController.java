package com.njfu.wa.biz.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BizController {

    @RequestMapping("/business")
    public String business() {
        return "biz/business";
    }
}
