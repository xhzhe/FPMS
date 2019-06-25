package com.fpms.dao;

import com.fpms.entity.LogMoney;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface LogMoneyDao {
    int deleteByPrimaryKey(Integer logMoneyId);

    int insert(LogMoney record);

    int insertSelective(LogMoney record);

    LogMoney selectByPrimaryKey(Integer logMoneyId);

    int updateByPrimaryKeySelective(LogMoney record);

    int updateByPrimaryKey(LogMoney record);

    List<LogMoney> selectAll();
}