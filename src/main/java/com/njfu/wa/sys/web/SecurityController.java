package com.njfu.wa.sys.web;


import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    /**
     * 登录页面
     *
     * @return Page
     */
    @RequestMapping("")
    public String loginPage() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            // 若已通过认证，转到首页
            return "redirect:/sys";
        }
        return "login";
    }

    /**
     * 验证登录
     *
     * @return json data
     */
    @PostMapping(value = "/login")
    public @ResponseBody
    Result login(String username, String password) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // 登录失败：包括账户不存在、密码错误等，都会抛出ShiroException
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            LOGGER.info("{} login", subject.getPrincipal());
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
     * 未授权跳转
     *
     * @param model model
     * @return errorPage
     */
    @GetMapping("/403")
    public String forbidden(Model model) {
        model.addAttribute("status", "403");
        return "error";
    }
}