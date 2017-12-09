package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Permission;
import com.njfu.wa.sys.domain.Role;
import com.njfu.wa.sys.domain.User;

import java.util.List;
import java.util.Set;

public interface SecurityService {

    /**
     * 查询指定用户帐号对应的密码和盐
     *
     * @param username username
     * @return User
     */
    User getUser(String username);

    /**
     * 查询指定账号对应的角色
     *
     * @param roleName roleName
     * @param userId userId
     * @return Roles
     */
    List<Role> getRoles(String roleName, Integer userId);

    /**
     * 查询指定角色对应的权限
     *
     * @param roles roles
     * @return Permissions
     */
    List<Permission> getPermissionsByRoles(List<Role> roles);

    /**
     * 查询指定账号拥有的权限
     *
     * @param userId userId
     * @return StringPermissions
     */
    Set<String> getStringPermissions(int userId);

    /**
     * 获取系统所有注册权限
     *
     * @return Permissions
     */
    List<Permission> getPermissions();

    /**
     * 获取用户信息
     *
     * @param username username
     * @return Users
     */
    List<User> getUsers(String username);

    /**
     * 新增用户
     * @param user user
     */
    void addUser(User user);
}
