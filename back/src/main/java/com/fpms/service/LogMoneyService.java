package com.fpms.service;

import com.fpms.entity.LogMoney;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/25 10:51
 * @description:
 * @modified :
 */
public interface LogMoneyService {

    /**
     *  获取所有资金变动记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 10:58
     * @param
     * @return     : java.util.List<com.fpms.entity.LogMoney>
     */
    List<LogMoney> selectAllLogMoney();

    void addLogMoney(LogMoney logMoney);

}
