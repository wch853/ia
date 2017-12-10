package com.njfu.wa.sys.utils;

/**
 * 常量
 */
public interface CommonConstants {

    /**
     * shiro加密方式
     */
    String HASH_CREDENTIAL_NAME = "md5";

    /**
     * shiro hash次数
     */
    int HASH_ITERATIONS = 1;

    /**
     * 默认密码
     */
    String DEFAULT_PASSWORD = "@wa-user";

    /**
     * root账户id
     */
    int ROOT_USER_ID = 1;

    /**
     * 超级管理员角色id
     */
    int SUPER_ROLE_ID = 1;

    /**
     * 账号状态-有效
     */
    short VALID_USER_STATUS = 1;

    /**
     * 账号状态-无效
     */
    short INVALID_USER_STATUS = 0;

    /**
     * 所有角色
     */
    String ALL_ROLES = "allRoles";

    /**
     * 拥有角色
     */
    String HAS_ROLES = "hasRoles";

    /**
     * 所有权限
     */
    String ALL_PERMISSIONS = "allPermissions";

    /**
     * 拥有权限
     */
    String HAS_PERMISSIONS = "hasPermissions";

    /**
     * 是否使用ehcache缓存
     */
    boolean USE_EHCACHE = true;
}
