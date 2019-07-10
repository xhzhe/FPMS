package com.fpms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 职工角色实体类
 * @modified :
 */
public class StaffRole {
    private Integer staffRoleId;

    private Integer staffId;

    private Integer roleId;

    private Date createTime;

    public Integer getStaffRoleId() {
        return staffRoleId;
    }

    public void setStaffRoleId(Integer staffRoleId) {
        this.staffRoleId = staffRoleId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}