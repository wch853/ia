package com.njfu.wa.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.wa.sys.domain.Role;
import com.njfu.wa.sys.domain.User;
import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.service.SecurityService;
import com.njfu.wa.sys.utils.PaginationResult;
import com.njfu.wa.sys.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限管理
 */
@Controller
@RequestMapping("/sys/auth")
public class AuthController {

    @Resource
    private SecurityService securityService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    /**
     * 权限管理页面
     *
     * @return Page
     */
    @GetMapping("")
    public String auth() {
        return "sys/auth/auth";
    }

    /**
     * 获取用户列表
     *
     * @param offset   offset
     * @param limit    limit
     * @param username username
     * @return json data
     */
    @GetMapping("/user")
    public @ResponseBody
    PaginationResult<User> getUsers(int offset, int limit, @RequestParam("name") String username) {
        PageHelper.offsetPage(offset, limit);
        List<User> users = securityService.getUsers(username);
        PageInfo<User> page = new PageInfo<>(users);
        return new PaginationResult<>(page.getTotal(), users);
    }

    /**
     * 创建用户
     *
     * @param user user
     * @return json Result
     */
    @PostMapping("/user/add")
    public @ResponseBody
    Result addUser(User user) {
        try {
            securityService.addUser(user);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 获取角色列表
     *
     * @param offset   offset
     * @param limit    limit
     * @param roleName roleName
     * @return json data
     */
    @GetMapping("/role")
    public @ResponseBody
    PaginationResult<Role> getRoles(int offset, int limit, @RequestParam("name") String roleName) {
        PageHelper.offsetPage(offset, limit);
        List<Role> roles = securityService.getRoles(roleName, null);
        PageInfo<Role> page = new PageInfo<>(roles);
        return new PaginationResult<>(page.getTotal(), roles);
    }
}
