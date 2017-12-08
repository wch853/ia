package com.wch.test.service.impl;


import com.wch.test.domain.Permission;
import com.wch.test.domain.Role;
import com.wch.test.domain.User;
import com.wch.test.mapper.SecurityMapper;
import com.wch.test.service.SecurityService;
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
    public User getPasswordAndSalt(String username) {
        return securityMapper.selectPasswordAndSalt(username);
    }

    /**
     * 查询指定账号对应的角色
     *
     * @param userId userId
     * @return Roles
     */
    @Override
    public List<Role> getRoles(int userId) {
        return securityMapper.selectRoles(userId);
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
}
