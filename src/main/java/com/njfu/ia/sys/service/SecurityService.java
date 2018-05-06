package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Permission;
import com.njfu.ia.sys.domain.Role;
import com.njfu.ia.sys.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SecurityService {

    /**
     * 查询指定用户帐号对应的密码和盐
     *
     * @param username username
     * @return User
     */
    User queryUser(String username);

    /**
     * 查询指定账号对应的角色
     *
     * @param roleName roleName
     * @param userId   userId
     * @return Roles
     */
    List<Role> queryRoles(String roleName, Integer userId);

    /**
     * 查询权限
     *
     * @param roleId roleId
     * @return Permissions
     */
    List<Permission> queryPermissionsByRole(Integer roleId);

    /**
     * 查询指定账号拥有的权限
     *
     * @param userId userId
     * @return StringPermissions
     */
    Set<String> queryStringPermissions(int userId);

    /**
     * 获取系统所有注册权限
     *
     * @return Permissions
     */
    List<Permission> queryPermissions();

    /**
     * 获取用户信息
     *
     * @param username username
     * @return Users
     */
    List<User> queryUsers(String username);

    /**
     * 新增用户
     *
     * @param user user
     */
    void addUser(User user);

    /**
     * 分类获取用户已分配/未分配角色
     *
     * @param userId userId
     * @return Map
     */
    Map<String, List<Role>> classifyRoles(Integer userId);

    /**
     * 重新保存用户角色
     *
     * @param userId  userId
     * @param roleIds roleIds
     */
    void saveUserRoles(Integer userId, List<Integer> roleIds);

    /**
     * 修改用户信息
     *
     * @param user        user
     * @param prePassword prePassword
     */
    void modifyUser(User user, String prePassword);

    /**
     * 删除用户
     *
     * @param userId userId
     */
    void removeUser(Integer userId);

    /**
     * 新增角色
     *
     * @param role role
     */
    void addRole(Role role);

    /**
     * 分类获取已分配/未分配权限
     *
     * @param roleId roleId
     * @return map
     */
    Map<String, Object> classifyPermissions(Integer roleId);

    /**
     * 保存角色权限
     *
     * @param roleId        roleId
     * @param permissionIds permissionIds
     */
    void saveRolePermissions(Integer roleId, List<Integer> permissionIds);

    /**
     * 删除角色
     * 删除用户-角色关系
     * 删除角色-权限关系
     *
     * @param roleId roleId
     */
    void removeRole(Integer roleId);
}
