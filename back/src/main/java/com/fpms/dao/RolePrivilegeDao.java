package com.fpms.dao;

import com.fpms.entity.RolePrivilege;

public interface RolePrivilegeDao {
    int deleteByPrimaryKey(Integer rolePrivilegeId);

    int insert(RolePrivilege record);

    int insertSelective(RolePrivilege record);

    RolePrivilege selectByPrimaryKey(Integer rolePrivilegeId);

    int updateByPrimaryKeySelective(RolePrivilege record);

    int updateByPrimaryKey(RolePrivilege record);
}