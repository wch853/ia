package com.njfu.wa.sys.web;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.util.Result;
import com.njfu.wa.sys.service.BlockService;
import com.njfu.wa.sys.service.FieldStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlockService blockService;

    @Autowired
    private FieldStatusService fieldStatusService;

    /**
     * 系统管理首页
     * @param model data
     * @return page
     */
    @RequestMapping({"/", "/sys"})
    public String index(Model model) {
        List<Block> blocks = blockService.getBlocksAndFields();
        model.addAttribute("blocks", blocks);
        return "index";
    }

    @RequestMapping("/sys/getFieldStatus")
    public @ResponseBody
    Result getFieldStatus(String fieldId) {
        return fieldStatusService.getFieldStatusById(fieldId);
    }

}
