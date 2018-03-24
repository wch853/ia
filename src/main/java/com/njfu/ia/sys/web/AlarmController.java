package com.njfu.ia.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlarmController {

    @GetMapping("/alarm/record")
    public String alarmRecord() {
        return "sys/alarm/record";
    }
}
