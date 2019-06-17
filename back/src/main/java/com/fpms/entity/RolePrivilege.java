package com.fpms.entity;

import java.util.Date;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 角色权限实体类
 * @modified :
 */
public class RolePrivilege {
    private Integer rolePrivilegeId;

    private Integer roleId;

    private Integer privilegeId;

    private Date createTime;

    public Integer getRolePrivilegeId() {
        return rolePrivilegeId;
    }

    public void setRolePrivilegeId(Integer rolePrivilegeId) {
        this.rolePrivilegeId = rolePrivilegeId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}