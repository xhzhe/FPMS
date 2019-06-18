package com.fpms.dao;

import com.fpms.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface UserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    /**
     * 通过用户名查找用户
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/18 19:08
     * @param       userName
     * @return     : com.fpms.entity.User
     */
    User selectByUserName(String userName);
}