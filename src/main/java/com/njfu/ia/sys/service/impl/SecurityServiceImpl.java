package com.njfu.ia.sys.service.impl;


import com.njfu.ia.sys.domain.Permission;
import com.njfu.ia.sys.domain.Role;
import com.njfu.ia.sys.domain.User;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.SecurityMapper;
import com.njfu.ia.sys.service.SecurityService;
import com.njfu.ia.sys.shiro.AuthRealm;
import com.njfu.ia.sys.utils.CommonConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Resource
    private SecurityMapper securityMapper;

    @Resource
    private AuthRealm authRealm;

    /**
     * 查询指定用户帐号对应的密码和盐
     *
     * @param username username
     * @return User
     */
    @Override
    public User getUser(String username) {
        return securityMapper.selectUser(username);
    }

    /**
     * 查询角色
     *
     * @param roleName roleName
     * @param userId   userId
     * @return Roles
     */
    @Override
    public List<Role> getRoles(String roleName, Integer userId) {
        return securityMapper.selectRoles(roleName, userId);
    }

    /**
     * 查询权限
     *
     * @param roleId roleId
     * @return Permissions
     */
    @Override
    public List<Permission> getPermissionsByRole(Integer roleId) {
        return securityMapper.selectPermissions(roleId);
    }

    /**
     * 查询指定账号拥有的权限
     *
     * @param userId userId
     * @return StringPermissions
     */
    @Override
    public Set<String> getStringPermissions(int userId) {
        List<String> permissions = securityMapper.selectStringPermissions(userId);
        // 通过账号拥有的角色查询出的权限可能有重复，通过Set去重
        return new HashSet<>(permissions);
    }

    /**
     * 获取系统所有注册权限
     *
     * @return Permissions
     */
    @Override
    public List<Permission> getPermissions() {
        return securityMapper.selectPermissions(null);
    }

    /**
     * 获取用户信息
     *
     * @param name name
     * @return Users
     */
    @Override
    public List<User> getUsers(String name) {
        return securityMapper.selectUsers(name);
    }

    /**
     * 新增用户
     *
     * @param user user
     */
    @Override
    public void addUser(User user) {
        this.resetSaltAndPassword(user);
        user.setStatus(CommonConstants.VALID_USER_STATUS);
        int count = securityMapper.insertUser(user);
        if (count <= 0) {
            throw new BusinessException("创建用户失败！");
        }
    }

    /**
     * 重新设置账号的salt和password
     *
     * @param user user
     */
    private void resetSaltAndPassword(User user) {
        // 以创建时间为salt
        String salt = String.valueOf(System.currentTimeMillis());
        // 重新hash生成密码
        String originPassword = user.getPassword() == null ? CommonConstants.DEFAULT_PASSWORD : user.getPassword();
        String password = this.hashPassword(originPassword, salt);
        user.setSalt(salt);
        user.setPassword(password);
    }

    /**
     * 对原密码进行hash
     *
     * @param originPassword originPassword
     * @param salt           salt
     * @return password
     */
    private String hashPassword(String originPassword, String salt) {
        return new SimpleHash(CommonConstants.HASH_CREDENTIAL_NAME, originPassword,
                ByteSource.Util.bytes(salt), CommonConstants.HASH_ITERATIONS).toString();
    }

    /**
     * 分类获取用户已分配/未分配角色
     *
     * @param userId userId
     * @return Map
     */
    @Override
    public Map<String, List<Role>> classifyRoles(Integer userId) {
        List<Role> hasRoles = securityMapper.selectRoles(null, userId);
        List<Role> allRoles = securityMapper.selectRoles(null, null);

        Map<String, List<Role>> map = new HashMap<>();
        map.put(CommonConstants.HAS_ROLES, hasRoles);
        map.put(CommonConstants.ALL_ROLES, allRoles);
        return map;
    }

    /**
     * 重新保存用户角色
     *
     * @param userId  userId
     * @param roleIds roleIds 为空时删除该user所有的角色
     */
    @Override
    @Transactional
    public void saveUserRoles(Integer userId, List<Integer> roleIds) {
        if (null == userId) {
            throw new BusinessException("账号ID不可为空！");
        }
        if (CommonConstants.ROOT_USER_ID == userId) {
            throw new BusinessException("root账户不可编辑角色！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        // 删除该账号的所有角色记录
        securityMapper.deleteUserRoles(map);

        if (!CollectionUtils.isEmpty(roleIds)) {
            // role不为空，重新保存
            map.put("roleIds", roleIds);
            int count = securityMapper.insertUserRoles(map);
            if (count <= 0) {
                throw new BusinessException("保存用户角色失败！");
            }
        }

        if (CommonConstants.USE_EHCACHE) {
            // 清除权限缓存
            authRealm.clearCache();
        }
    }

    /**
     * 修改用户信息
     *
     * @param user        user
     * @param prePassword prePassword
     */
    @Override
    public void modifyUser(User user, String prePassword) {
        User currentUser;
        try {
            // 获取当前用户
            Subject subject = SecurityUtils.getSubject();
            currentUser = (User) subject.getPrincipal();
            if (null != user.getStatus() && !subject.isPermitted(CommonConstants.AUTH_PERM)) {
                throw new BusinessException("无权限修改用户账号状态");
            }
            if (null == user.getStatus()) {
                // 用户有权修改自己的账号名称、密码
                user.setId(currentUser.getId());
            }
        } catch (Exception e) {
            throw new BusinessException("修改用户信息失败！");
        }

        if (StringUtils.isEmpty(user.getName())) {
            user.setName(null);
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(null);
        }

        if (null != user.getName() && CommonConstants.ROOT_USER_ID == user.getId()) {
            throw new BusinessException("root账号不可修改账号名称");
        }

        if (null != user.getPassword()) {
            User dbUser = securityMapper.selectUser(currentUser.getUsername());
            if (!dbUser.getPassword().equals(this.hashPassword(prePassword, dbUser.getSalt()))) {
                throw new BusinessException("原密码输入不正确！");
            }
            // 重置密码
            this.resetSaltAndPassword(user);
        }

        int count = securityMapper.updateUser(user);
        if (count <= 0) {
            throw new BusinessException("修改用户信息失败！");
        }
    }

    /**
     * 删除用户
     *
     * @param userId userId
     */
    @Override
    @Transactional
    public void removeUser(Integer userId) {
        if (null == userId) {
            throw new BusinessException("账号ID不可为空！");
        }
        if (CommonConstants.ROOT_USER_ID == userId) {
            throw new BusinessException("root账户不可删除！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        // 删除该账号的所有角色记录
        securityMapper.deleteUserRoles(map);

        int count = securityMapper.deleteUser(userId);
        if (count <= 0) {
            throw new BusinessException("删除用户账号失败");
        }

        if (CommonConstants.USE_EHCACHE) {
            // 清除权限缓存
            authRealm.clearCache();
        }
    }

    /**
     * 新增角色
     *
     * @param role role
     */
    @Override
    public void addRole(Role role) {
        int count = securityMapper.insertRole(role);
        if (count <= 0) {
            throw new BusinessException("新增角色失败！");
        }
    }

    /**
     * 分类获取已分配/未分配权限
     *
     * @param roleId roleId
     * @return map
     */
    @Override
    public Map<String, Object> classifyPermissions(Integer roleId) {
        List<Permission> allPermissions = securityMapper.selectPermissions(null);
        List<Permission> hasPermissions = securityMapper.selectPermissions(roleId);

        Map<String, Object> map = new HashMap<>();
        map.put(CommonConstants.ALL_PERMISSIONS, allPermissions);
        map.put(CommonConstants.HAS_PERMISSIONS, hasPermissions);
        return map;
    }

    /**
     * 保存角色权限
     *
     * @param roleId        roleId
     * @param permissionIds permissionIds
     */
    @Override
    public void saveRolePermissions(Integer roleId, List<Integer> permissionIds) {
        if (CommonConstants.SUPER_ROLE_ID == roleId) {
            throw new BusinessException("超级管理员角色不可编辑权限！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        // 删除该角色所有权限
        securityMapper.deleteRolePermissions(map);

        if (!CollectionUtils.isEmpty(permissionIds)) {
            // permission不为空，重新保存
            map.put("permissionIds", permissionIds);
            int count = securityMapper.insertRolePermissions(map);
            if (count <= 0) {
                throw new BusinessException("保存角色权限失败！");
            }
        }

        if (CommonConstants.USE_EHCACHE) {
            // 清除权限缓存
            authRealm.clearCache();
        }
    }

    /**
     * 删除角色
     * 删除用户-角色关系
     * 删除角色-权限关系
     *
     * @param roleId roleId
     */
    @Override
    public void removeRole(Integer roleId) {
        if (null == roleId) {
            throw new BusinessException("角色ID不可为空！");
        }
        if (CommonConstants.SUPER_ROLE_ID == roleId) {
            throw new BusinessException("超级管理员不可删除！");
        }

        // 删除角色
        int count = securityMapper.deleteRole(roleId);
        if (count <= 0) {
            throw new BusinessException("删除角色失败！");
        }

        Map<String, Object> map = new HashMap<>();

        map.put("roleIds", Arrays.asList(roleId));
        // 删除用户-角色关系
        securityMapper.deleteUserRoles(map);

        map.remove("roleIds");
        map.put("roleId", roleId);
        // 删除角色-权限关系
        securityMapper.deleteRolePermissions(map);
    }
}
