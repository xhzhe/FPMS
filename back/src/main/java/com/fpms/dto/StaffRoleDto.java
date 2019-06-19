package com.fpms.dto;

import com.fpms.entity.Role;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/19 11:51
 * @description:职工与角色的关联信息封装类
 * @modified :
 */
public class StaffRoleDto {
    private Integer staffId;
    private String staffName;
    private List<Role> roleList;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
