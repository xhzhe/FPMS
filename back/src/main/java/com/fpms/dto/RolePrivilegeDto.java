package com.fpms.dto;

import com.fpms.entity.Privilege;

import java.util.Date;
import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/18 23:17
 * @description:角色与权限的关联信息封装类
 * @modified :
 */
public class RolePrivilegeDto {
    private Integer roleId;
    private String roleName;
    private List<Privilege> privilegeList;
    private Date createTime;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
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
