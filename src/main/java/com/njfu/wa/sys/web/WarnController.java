package com.njfu.wa.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sys/warn")
public class WarnController {

    @RequestMapping("/threshold")
    public String threshold() {
        return "sys/warn/thresholdSet";
    }
}
