package com.njfu.ia.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户
 */
public class User {

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 账号
     */
    private String username;

    /**
     * 邮箱地址
     */
    private String mail;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 盐
     */
    @JsonIgnore
    private String salt;

    /**
     * 账号状态 0-无效，1-有效
     */
    private Integer status;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
