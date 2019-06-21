package com.fpms.dao;

import com.fpms.entity.LogOperate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface LogOperateDao {
    /**
     *  通过主键删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:53
     * @param       logOperateId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer logOperateId);

    /**
     *  插入一条记录的全部字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:53
     * @param       record
     * @return     : int
     */
    int insert(LogOperate record);

    /**
     *  插入一条记录的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:53
     * @param       record
     * @return     : int
     */
    int insertSelective(LogOperate record);

    /**
     *  通过主键查询一条记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:53
     * @param       logOperateId
     * @return     : com.fpms.entity.LogOperate
     */
    LogOperate selectByPrimaryKey(Integer logOperateId);

    /**
     *  通过主键更新一条记录中的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:54
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(LogOperate record);

    /**
     *  通过主键更新一条记录中的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:54
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(LogOperate record);

    /**
     * 查询并返回所有记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:30
     * @param
     * @return     : java.util.List<com.fpms.entity.LogOperate>
     */
    List<LogOperate> findAllLogOperate();
}