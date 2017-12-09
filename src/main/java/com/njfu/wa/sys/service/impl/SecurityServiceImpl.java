package com.njfu.wa.sys.service.impl;


import com.njfu.wa.sys.domain.Permission;
import com.njfu.wa.sys.domain.Role;
import com.njfu.wa.sys.domain.User;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.mapper.SecurityMapper;
import com.njfu.wa.sys.service.SecurityService;
import com.njfu.wa.sys.utils.CommonConstants;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Resource
    private SecurityMapper securityMapper;

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
     * 查询指定角色对应的权限
     *
     * @param roles roles
     * @return Permissions
     */
    @Override
    public List<Permission> getPermissionsByRoles(List<Role> roles) {
        return securityMapper.selectPermissionsByRoles(roles);
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
        return securityMapper.selectPermissions();
    }

    /**
     * 获取用户信息
     *
     * @param username username
     * @return Users
     */
    @Override
    public List<User> getUsers(String username) {
        return securityMapper.selectUsers(username);
    }

    /**
     * 新增用户
     *
     * @param user user
     */
    @Override
    public void addUser(User user) {
        this.setSaltAndPassword(user);
        int count = securityMapper.insertUser(user);
        if (count <= 0) {
            throw new BusinessException("创建用户失败！");
        }
    }

    /**
     * 重新设置账号的salt和Password
     *
     * @param user user
     */
    private void setSaltAndPassword(User user) {
        // 以创建时间为salt
        String salt = String.valueOf(System.currentTimeMillis());
        // 写入数据库中
        String password = new SimpleHash(CommonConstants.HASH_CREDENTIAL_NAME, CommonConstants.DEFAULT_PASSWORD,
                ByteSource.Util.bytes(salt), CommonConstants.HASH_ITERATIONS).toString();
        user.setSalt(salt);
        user.setPassword(password);
    }
}
