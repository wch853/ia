package com.njfu.ia.sys.utils;

/**
 * 常量
 */
public interface CommonConstants {
    // TODO JMX
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
    String DEFAULT_PASSWORD = "@ia-user";

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
     * 邮件推送-是
     */
    short SEND_WARN_MAIL = 1;

    /**
     * 邮件推送报警次数最低限制
     */
    int SEND_MAIL_WARN_COUNT = 1;

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

    /**
     * 是否开启报警邮件推送
     */
    boolean USE_WARN_MAIL = true;

    /**
     * 权限管理标识符
     */
    String AUTH_PERM = "sys:auth";

    /**
     * 报警管理权限标识符
     */
    String WARN_PERM = "sys:warn:query";

    /**
     * 扫描定时任务设置
     */
    String SCAN_FIELD_STATUS_CRON = "0 0 0/1 * * ?";

    /**
     * websocket origin
     */
    String WS_ORIGIN = "http://106.14.200.121";

    /**
     * Date字符串格式化patter(second)
     */
    String DATE_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date字符串格式化patter(minute)
     */
    String DATE_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
}
