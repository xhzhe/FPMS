package com.fpms.entity;

import java.util.Date;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 角色实体类
 * @modified :
 */
public class Role {
    private Integer roleId;

    private String roleName;

    private Date createTime;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}