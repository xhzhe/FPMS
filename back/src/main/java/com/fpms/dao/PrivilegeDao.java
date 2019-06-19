package com.fpms.dao;

import com.fpms.entity.Privilege;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface PrivilegeDao {
    int deleteByPrimaryKey(Integer privilegeId);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer privilegeId);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);

    List<Privilege> findAllPrivileges();
}