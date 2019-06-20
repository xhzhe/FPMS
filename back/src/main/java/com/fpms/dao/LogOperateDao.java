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
    int deleteByPrimaryKey(Integer logOperateId);

    int insert(LogOperate record);

    int insertSelective(LogOperate record);

    LogOperate selectByPrimaryKey(Integer logOperateId);

    int updateByPrimaryKeySelective(LogOperate record);

    int updateByPrimaryKey(LogOperate record);

    /**
     *
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:30
     * @param
     * @return     : java.util.List<com.fpms.entity.LogOperate>
     */
    List<LogOperate> findAllLogOperate();
}