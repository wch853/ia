package com.njfu.ia.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.ia.sys.domain.Role;
import com.njfu.ia.sys.domain.User;
import com.njfu.ia.sys.enums.ResultEnum;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.service.SecurityService;
import com.njfu.ia.sys.utils.PaginationResult;
import com.njfu.ia.sys.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 权限管理
 */
@Controller
@RequestMapping("/sys/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private SecurityService securityService;

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
     * @param offset offset
     * @param limit  limit
     * @param name   name
     * @return json data
     */
    @GetMapping("/user")
    public @ResponseBody
    PaginationResult<User> getUsers(Integer offset, Integer limit, String name) {
        PageHelper.offsetPage(offset, limit);
        List<User> users = securityService.queryUsers(name);
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
            LOGGER.error(e.getMessage(), e);
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
    PaginationResult<Role> getRoles(Integer offset, Integer limit, @RequestParam("name") String roleName) {
        PageHelper.offsetPage(offset, limit);
        List<Role> roles = securityService.queryRoles(roleName, null);
        PageInfo<Role> page = new PageInfo<>(roles);
        return new PaginationResult<>(page.getTotal(), roles);
    }

    /**
     * 查询所有角色/指定账户拥有的角色
     *
     * @param userId userId
     * @return json Result
     */
    @GetMapping("/user/role")
    public @ResponseBody
    Result getPrivateRoles(Integer userId) {
        try {
            Map<String, List<Role>> classifyRoles = securityService.classifyRoles(userId);
            return Result.response(ResultEnum.DATA, classifyRoles);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 重新保存用户权限
     *
     * @param userId  userId
     * @param roleIds roleIds
     * @return json Result
     */
    @PostMapping("/user/role/add")
    public @ResponseBody
    Result saveUserRoles(Integer userId,
                         @RequestParam(value = "roleIds[]", required = false) List<Integer> roleIds) {
        try {
            securityService.saveUserRoles(userId, roleIds);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改用户信息
     *
     * @param user user
     * @return json Result
     */
    @PostMapping("/user/modify")
    public @ResponseBody
    Result modifyUser(User user, String prePassword) {
        try {
            securityService.modifyUser(user, prePassword);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除用户
     *
     * @param userId userId
     * @return json Result
     */
    @PostMapping("/user/remove")
    public @ResponseBody
    Result removeUser(Integer userId) {
        try {
            securityService.removeUser(userId);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 新增角色
     *
     * @param role role
     * @return json Result
     */
    @PostMapping("/role/add")
    public @ResponseBody
    Result addRole(Role role) {
        try {
            securityService.addRole(role);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 查询所有权限/已分配权限
     *
     * @param roleId roleId
     * @return json Result
     */
    @GetMapping("/role/permission")
    public @ResponseBody
    Result getRolePermissions(Integer roleId) {
        try {
            Map<String, Object> map = securityService.classifyPermissions(roleId);
            return Result.response(ResultEnum.DATA, map);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 保存角色权限
     *
     * @param roleId        roleId
     * @param permissionIds permissionIds
     * @return json Result
     */
    @PostMapping("/role/permission/add")
    public @ResponseBody
    Result addRolePermissions(Integer roleId,
                              @RequestParam(value = "permissionIds[]", required = false) List<Integer> permissionIds) {
        try {
            securityService.saveRolePermissions(roleId, permissionIds);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除角色
     *
     * @param roleId roleId
     * @return json Result
     */
    @PostMapping("/role/remove")
    public @ResponseBody
    Result removeRole(Integer roleId) {
        try {
            securityService.removeRole(roleId);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }
}