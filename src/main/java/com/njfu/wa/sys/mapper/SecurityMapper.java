package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Permission;
import com.njfu.wa.sys.domain.Role;
import com.njfu.wa.sys.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SecurityMapper {

    /**
     * 查询指定账户
     *
     * @param username username
     * @return User
     */
    User selectUser(@Param("username") String username);

    /**
     * 查询角色
     *
     * @param roleName roleName
     * @param userId   userId
     * @return Roles
     */
    List<Role> selectRoles(@Param("roleName") String roleName, @Param("userId") Integer userId);

    /**
     * 查询指定角色拥有的权限
     *
     * @param roles roles
     * @return Permissions
     */
    List<Permission> selectPermissionsByRoles(@Param("roles") List<Role> roles);

    /**
     * 查询指定用户拥有的权限
     *
     * @param userId userId
     * @return StringPermissions
     */
    List<String> selectStringPermissions(@Param("userId") int userId);

    /**
     * 查询所有注册权限
     *
     * @return Permissions
     */
    List<Permission> selectPermissions();

    /**
     * 创建角色
     *
     * @param role role
     * @return count
     */
    int insertRole(Role role);

    /**
     * 删除角色
     *
     * @param roleId roleId
     * @return count
     */
    int deleteRole(@Param("roleId") int roleId);

    /**
     * 为角色赋权
     *
     * @param map map
     * @return count
     */
    int insertRolePermissions(Map<String, Object> map);

    /**
     * 删除角色权限
     *
     * @param map map
     * @return count
     */
    int deleteRolePermissions(Map<String, Object> map);

    /**
     * 为用户添加角色
     *
     * @param map map
     * @return count
     */
    int insertUserRoles(Map<String, Object> map);

    /**
     * 伤处用户角色
     *
     * @param map map
     * @return count
     */
    int deleteUserRoles(Map<String, Object> map);

    /**
     * 查询用户
     *
     * @param username username
     * @return Users
     */
    List<User> selectUsers(@Param("username") String username);

    /**
     * 新增用户
     *
     * @param user user
     * @return count
     */
    int insertUser(User user);

    /**
     * 修改用户
     *
     * @param user user
     * @return count
     */
    int updateUser(User user);

    /**
     * 删除用户
     *
     * @param userId userId
     * @return count
     */
    int deleteUser(@Param("userId") int userId);
}
