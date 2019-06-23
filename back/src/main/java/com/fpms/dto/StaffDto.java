package com.fpms.dto;

import com.fpms.entity.Role;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/18 20:27
 * @description: 封装职工特定的信息的数据对象
 * @modified :
 */
public class StaffDto {

    private Integer staffId;

    private String staffName;

    private String staffGender;

    private String staffPhone;

    private Byte staffStatus;

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

    public String getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public Byte getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(Byte staffStatus) {
        this.staffStatus = staffStatus;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
