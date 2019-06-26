package com.fpms.dto;

import com.fpms.entity.Role;

import java.util.Date;
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

    private String staffEmail;

    private String staffDepartment;

    private Date createTime;

    private Byte staffStatus;

    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffDepartment() {
        return staffDepartment;
    }

    public void setStaffDepartment(String staffDepartment) {
        this.staffDepartment = staffDepartment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
