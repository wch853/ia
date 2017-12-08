package com.wch.test.service;

import com.wch.test.domain.Permission;
import com.wch.test.domain.Role;
import com.wch.test.domain.User;

import java.util.List;
import java.util.Set;

public interface SecurityService {

    /**
     * 查询指定用户帐号对应的密码和盐
     *
     * @param username username
     * @return User
     */
    User getPasswordAndSalt(String username);

    /**
     * 查询指定账号对应的角色
     *
     * @param userId userId
     * @return Roles
     */
    List<Role> getRoles(int userId);

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
}
