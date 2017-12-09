package com.njfu.wa.sys.web;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.FieldStatus;
import com.njfu.wa.sys.domain.User;
import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.service.BlockService;
import com.njfu.wa.sys.service.FieldStatusService;
import com.njfu.wa.sys.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class IndexController {

    @Resource
    private BlockService blockService;

    @Resource
    private FieldStatusService fieldStatusService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    /**
     * 系统管理首页
     *
     * @param model data
     * @return Page
     */
    @GetMapping
    public String index(Model model) {
        List<Block> blocks = blockService.getBlocksAndFields();
        model.addAttribute("blocks", blocks);
        return "sys/index";
    }

    /**
     * 首页悬浮获取大棚状态
     *
     * @param fieldId fieldId
     * @return json data
     */
    @GetMapping("/status")
    public @ResponseBody
    Result getFieldStatus(String fieldId) {
        FieldStatus status = fieldStatusService.getFieldStatusById(fieldId);
        return Result.response(ResultEnum.DATA, status);
    }

    /**
     * 获取用户信息
     *
     * @return name
     */
    @GetMapping("/user/info")
    public @ResponseBody
    Result getUserInfo() {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            String name = user.getName();
            return Result.response(ResultEnum.DATA, name);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }
}
