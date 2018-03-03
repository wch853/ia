package com.njfu.ia.sys.domain;

/**
 * 角色
 */
public class Role {

    /**
     * 角色编号
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
