package com.njfu.ia.sys.web;

import com.njfu.ia.sys.domain.User;
import com.njfu.ia.sys.enums.ResultEnum;
import com.njfu.ia.sys.service.BlockService;
import com.njfu.ia.sys.utils.Constants;
import com.njfu.ia.sys.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys")
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private BlockService blockService;

    /**
     * 系统管理首页
     *
     * @return Page
     */
    @GetMapping("")
    public String index() {
        return "sys/index";
    }

    /**
     * 获取用户名称信息
     *
     * @return name
     */
    @GetMapping("/user/info")
    public @ResponseBody
    Result getUserInfo() {
        if (Constants.USE_SHIRO) {
            try {
                User user = (User) SecurityUtils.getSubject().getPrincipal();
                String name = user.getName();
                return Result.response(ResultEnum.DATA, name);
            } catch (Exception e) {
                LOGGER.error("Security get user info Exception", e);
                return Result.response(ResultEnum.FAIL);
            }
        } else {
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 跳转个人设置页
     *
     * @return
     */
    @GetMapping("/user")
    public String user() {
        return "sys/auth/user";
    }
}
