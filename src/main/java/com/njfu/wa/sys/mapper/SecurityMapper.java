package com.wch.test.mapper;

import com.wch.test.domain.Permission;
import com.wch.test.domain.Role;
import com.wch.test.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SecurityMapper {

    /**
     * 查询指定账户的密码和盐
     *
     * @param username username
     * @return User
     */
    User selectPasswordAndSalt(@Param("username") String username);

    /**
     * 查询指定账户拥有的角色
     *
     * @param userId userId
     * @return Roles
     */
    List<Role> selectRoles(@Param("userId") int userId);

    /**
     * 查询指定角色拥有的权限
     *
     * @param roles roles
     * @return Permissions
     */
    List<Permission> selectPermissionsByRoles(@Param("roles") List<Role> roles);

    /**
     * 查询指定用户拥有的权限
     * @param userId userId
     * @return StringPermissions
     */
    List<String> selectStringPermissions(@Param("userId") int userId);
}
