package com.fpms.dao;

import com.fpms.entity.LogOperate;
import org.springframework.stereotype.Component;

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
}