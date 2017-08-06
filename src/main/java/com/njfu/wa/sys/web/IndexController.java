package com.njfu.wa.sys.web;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlockService blockService;

    @RequestMapping({"/", "/sys"})
    public String index(Model model) {
        List<Block> blocks = blockService.getBlocksAndFields();
        model.addAttribute("blocks", blocks);
        return "index";
    }

}
