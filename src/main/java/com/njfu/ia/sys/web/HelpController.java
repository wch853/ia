package com.njfu.ia.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/help")
public class HelpController {

    /**
     * 帮助文档页
     *
     * @return Page
     */
    @RequestMapping("")
    public String document() {
        return "sys/help/document";
    }
}
