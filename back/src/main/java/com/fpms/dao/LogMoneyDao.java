package com.fpms.dao;

import com.fpms.entity.LogMoney;

public interface LogMoneyDao {
    int deleteByPrimaryKey(Integer logMoneyId);

    int insert(LogMoney record);

    int insertSelective(LogMoney record);

    LogMoney selectByPrimaryKey(Integer logMoneyId);

    int updateByPrimaryKeySelective(LogMoney record);

    int updateByPrimaryKey(LogMoney record);
}