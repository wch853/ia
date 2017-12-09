package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Permission;
import com.njfu.wa.sys.domain.Role;
import com.njfu.wa.sys.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SecurityMapperTest {

    @Resource
    private SecurityMapper securityMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityMapperTest.class);

    private final String username = "wch853";

    private final int id = 1;

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
    public void selectPermissionsByRoles() throws Exception {
    }

    @Test
    public void selectStringPermissions() throws Exception {
    }

    @Test
    public void selectPermissions() throws Exception {
        List<Permission> permissions = securityMapper.selectPermissions();
        LOGGER.info("permissions: {}", permissions);
    }

    @Test
    public void insertRole() throws Exception {
    }

    @Test
    public void deleteRole() throws Exception {
    }

    @Test
    public void insertRolePermissions() throws Exception {
    }

    @Test
    public void deleteRolePermissions() throws Exception {
    }

    @Test
    public void insertUserRoles() throws Exception {
    }

    @Test
    public void deleteUserRoles() throws Exception {
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
        user.setStatus((short) 1);

        int res = securityMapper.insertUser(user);
        Assert.assertEquals(1, res);
    }

    @Test
    public void updateUser() throws Exception {
    }

    @Test
    public void deleteUser() throws Exception {
    }

}