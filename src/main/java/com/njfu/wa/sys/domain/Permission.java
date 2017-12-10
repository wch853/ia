package com.njfu.wa.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 权限
 */
public class Permission {

    /**
     * 权限编号
     */
    private Integer id;

    /**
     * url地址
     */
    @JsonIgnore
    private String url;

    /**
     * url描述
     */
    private String urlName;

    /**
     * 权限标识符
     */
    @JsonIgnore
    private String perm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", urlName='" + urlName + '\'' +
                ", perm='" + perm + '\'' +
                '}';
    }
}
