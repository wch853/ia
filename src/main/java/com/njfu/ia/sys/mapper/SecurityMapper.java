package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Permission;
import com.njfu.ia.sys.domain.Role;
import com.njfu.ia.sys.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SecurityMapper {

    /**
     * 查询指定账户
     *
     * @param username
     * @return
     */
    User selectUser(@Param("username") String username);

    /**
     * 查询角色
     *
     * @param roleName
     * @param userId
     * @return
     */
    List<Role> selectRoles(@Param("roleName") String roleName, @Param("userId") Integer userId);

    /**
     * 查询权限(除去id=1的全权)
     *
     * @param roleId
     * @return
     */
    List<Permission> selectPermissions(@Param("roleId") Integer roleId);

    /**
     * 查询指定用户拥有的权限
     *
     * @param userId
     * @return
     */
    List<String> selectStringPermissions(@Param("userId") int userId);

    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    int deleteRole(@Param("roleId") int roleId);

    /**
     * 为角色赋权
     *
     * @param map
     * @return
     */
    int insertRolePermissions(Map<String, Object> map);

    /**
     * 删除角色权限
     *
     * @param map
     * @return
     */
    int deleteRolePermissions(Map<String, Object> map);

    /**
     * 为用户添加角色
     *
     * @param map
     * @return
     */
    int insertUserRoles(Map<String, Object> map);

    /**
     * 删除用户角色
     *
     * @param map
     * @return
     */
    int deleteUserRoles(Map<String, Object> map);

    /**
     * 查询用户
     *
     * @param name
     * @return
     */
    List<User> selectUsers(@Param("name") String name);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    int deleteUser(@Param("userId") int userId);
}
