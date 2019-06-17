package com.fpms.dao;

import com.fpms.entity.StaffRole;

public interface StaffRoleDao {
    int deleteByPrimaryKey(Integer staffRoleId);

    int insert(StaffRole record);

    int insertSelective(StaffRole record);

    StaffRole selectByPrimaryKey(Integer staffRoleId);

    int updateByPrimaryKeySelective(StaffRole record);

    int updateByPrimaryKey(StaffRole record);
}