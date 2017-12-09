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
     * 默认账号状态
     */
    int DEFALUT_USER_STATUS = 1;
}
