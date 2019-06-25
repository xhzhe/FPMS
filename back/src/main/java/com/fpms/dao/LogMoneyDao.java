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
    /**
     *  通过主键删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 11:28
     * @param       logMoneyId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer logMoneyId);

    /**
     *  插入记录的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 11:28
     * @param       record
     * @return     : int
     */
    int insert(LogMoney record);

    /**
     *  插入记录的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 11:28
     * @param       record
     * @return     : int
     */
    int insertSelective(LogMoney record);

    /**
     *  通过主键查询记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 11:29
     * @param       logMoneyId
     * @return     : com.fpms.entity.LogMoney
     */
    LogMoney selectByPrimaryKey(Integer logMoneyId);

    /**
     *  通过主键更新记录的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 11:29
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(LogMoney record);

    /**
     *  通过主键更新记录的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 11:29
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(LogMoney record);

    /**
     *  通过userId查询记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 11:29
     * @param       userId
     * @return     : java.util.List<com.fpms.entity.LogMoney>
     */
    List<LogMoney> selectAllByUserId(Integer userId);
}