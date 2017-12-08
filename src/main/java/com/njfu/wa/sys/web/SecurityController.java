package com.wch.test.web;

import com.wch.test.domain.User;
import com.wch.test.enums.ResultEnum;
import com.wch.test.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    /**
     * 登录页面
     *
     * @return Page
     */
    @RequestMapping
    public String loginPage() {
        return "login";
    }

    /**
     * 验证登录
     *
     * @return json data
     * @throws ShiroException ShiroException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Result login(String username, String password) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // 登录失败：包括账户不存在、密码错误等，都会抛出ShiroException
            SecurityUtils.getSubject().login(token);
            return Result.response(ResultEnum.SUCCESS);
        } catch (ShiroException e) {
            LOGGER.error("登录失败，{}，{}", e.getClass().getName(), e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * successUrl
     * 使用注解 @RequiresAuthentication 来标注该访问该url需要认证
     *
     * @param model model
     * @return Page
     */
    @RequestMapping("/index")
    @RequiresAuthentication
    public String home(Model model) {
        // 获取在身份认证时放入的身份信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("name", user.getName());
        return "index";
    }

    /**
     * unauthorizedUrl，未授权时跳转该url
     *
     * @return json
     */
    // @ExceptionHandler(UnauthorizedException.class)
    // @RequiresAuthentication
    // public @ResponseBody
    // String forbidden() {
    //     return "403";
    // }

    /**
     * 使用 @RequiresPermissions 注解来标注访问该url需要 "user:query" 权限
     *
     * @return json
     */
    @RequestMapping("/query")
    @RequiresPermissions("user:query")
    public @ResponseBody
    String query() {
        return "permit query.";
    }

    @RequestMapping("/add")
    @RequiresPermissions("user:add")
    public @ResponseBody
    String add() {
        return "permit add.";
    }

    @RequestMapping("/update")
    @RequiresPermissions("user:update")
    public @ResponseBody
    String update() {
        return "permit update.";
    }

    @RequestMapping("/delete")
    @RequiresPermissions("user:delete")
    public @ResponseBody
    String delete() {
        return "permit delete.";
    }

    @RequestMapping("/root")
    @RequiresPermissions("user")
    public @ResponseBody
    String root() {
        return "permit root.";
    }
}