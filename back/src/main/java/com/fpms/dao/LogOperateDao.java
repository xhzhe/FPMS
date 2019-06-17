package com.fpms.dao;

import com.fpms.entity.LogOperate;

public interface LogOperateDao {
    int deleteByPrimaryKey(Integer logOperateId);

    int insert(LogOperate record);

    int insertSelective(LogOperate record);

    LogOperate selectByPrimaryKey(Integer logOperateId);

    int updateByPrimaryKeySelective(LogOperate record);

    int updateByPrimaryKey(LogOperate record);
}