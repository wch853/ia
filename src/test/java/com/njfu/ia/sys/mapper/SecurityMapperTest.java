package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Permission;
import com.njfu.ia.sys.domain.Role;
import com.njfu.ia.sys.domain.User;
import com.njfu.ia.sys.utils.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SecurityMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityMapperTest.class);
    private final String username = "wch853";
    private final int id = 1;
    @Resource
    private SecurityMapper securityMapper;

    @Test
    public void selectUser() throws Exception {
        User user = securityMapper.selectUser(username);
        LOGGER.info("user: {}", user);
    }

    @Test
    public void selectRoles() throws Exception {
        String roleName = "超级管理员";
        List<Role> roles = securityMapper.selectRoles(roleName, id);
        LOGGER.info("roles: {}", roles);
    }

    @Test
    public void selectPermissions() throws Exception {
        List<Permission> permissions = securityMapper.selectPermissions(null);
        LOGGER.info("permissions: {}", permissions);
    }

    @Test
    public void selectStringPermissions() throws Exception {
        List<String> permissions = securityMapper.selectStringPermissions(id);
        LOGGER.info("permissions: {}", permissions);
    }

    @Test
    public void insertRole() throws Exception {
        String roleName = "test";
        Role role = new Role();
        role.setRoleName(roleName);
        int count = securityMapper.insertRole(role);
        Assert.assertEquals(1, count);
    }

    @Test
    public void deleteRole() throws Exception {
        int res = securityMapper.deleteRole(id);
        LOGGER.info("res: {}", res);
    }

    @Test
    public void insertRolePermissions() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", id);
        map.put("permissionIds", Arrays.asList(1222, 111));

        int res = securityMapper.insertRolePermissions(map);
        LOGGER.info("res: {}", res);
    }

    @Test
    public void deleteRolePermissions() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", id);

        int res = securityMapper.deleteRolePermissions(map);
        LOGGER.info("res: {}", res);
    }

    @Test
    public void insertUserRoles() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", id);
        map.put("roleIds", Arrays.asList(222, 333));
        int res = securityMapper.insertUserRoles(map);
        LOGGER.info("res: {}", res);
    }

    @Test
    public void deleteUserRoles() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", id);

        int res = securityMapper.deleteUserRoles(map);
        LOGGER.info("res: {}", res);
    }

    @Test
    public void selectUsers() throws Exception {
        List<User> users = securityMapper.selectUsers(username);
        LOGGER.info("users: {}", users);
    }

    @Test
    public void insertUser() throws Exception {
        User user = new User();
        user.setName("wch");
        user.setUsername("wch");
        user.setPassword("");
        user.setSalt("");
        user.setStatus(1);

        int res = securityMapper.insertUser(user);
        Assert.assertEquals(1, res);
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setId(id);
        user.setStatus(Constants.INVALID_USER_STATUS);

        int res = securityMapper.updateUser(user);
        Assert.assertEquals(1, res);
    }

    @Test
    public void deleteUser() throws Exception {
        int res = securityMapper.deleteUser(id);
        LOGGER.info("res: {}", res);
    }

}