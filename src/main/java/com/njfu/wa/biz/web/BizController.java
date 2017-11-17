package com.njfu.wa.biz.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BizController {

    @GetMapping("/business")
    public String business() {
        return "biz/business";
    }
}
