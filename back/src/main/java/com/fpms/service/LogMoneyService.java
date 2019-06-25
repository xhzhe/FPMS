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
     *  添加资金变动记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 11:26
     * @param       logMoney
     * @return     : void
     */
    void addLogMoney(LogMoney logMoney);

    /**
     *  通过userId获取用户的资金变动记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 11:26
     * @param       userId
     * @return     : java.util.List<com.fpms.entity.LogMoney>
     */
    List<LogMoney> selectAllLogMoneyByUserId(Integer userId);
}
