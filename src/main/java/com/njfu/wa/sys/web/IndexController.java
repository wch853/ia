package com.njfu.wa.sys.web;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.FieldStatus;
import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.service.BlockService;
import com.njfu.wa.sys.service.FieldStatusService;
import com.njfu.wa.sys.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private BlockService blockService;

    @Resource
    private FieldStatusService fieldStatusService;

    /**
     * 系统管理首页
     *
     * @param model data
     * @return page
     */
    @GetMapping({"/", "/sys"})
    public String index(Model model) {
        List<Block> blocks = blockService.getBlocksAndFields();
        model.addAttribute("blocks", blocks);
        return "index";
    }

    /**
     * 首页悬浮获取大棚状态
     *
     * @param fieldId fieldId
     * @return json data
     */
    @GetMapping("/sys/getFieldStatus")
    public @ResponseBody
    Result getFieldStatus(String fieldId) {
        FieldStatus status = fieldStatusService.getFieldStatusById(fieldId);
        return Result.response(ResultEnum.DATA, status);
    }

}
