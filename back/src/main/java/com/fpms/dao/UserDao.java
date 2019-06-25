package com.fpms.dao;

import com.fpms.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 用户表操作类
 * @modified :
 */
@Component
public interface UserDao {
    /**
     *  增加user
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 9:57
     * @param       user
     * @return     : int
     */
    int insert(User user);

    /**
     *  增加user
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 9:58
     * @param       user
     * @return     : int
     */
    int insertSelective(User user);

    /**
     *  通过用户id查找user
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 9:59
     * @param       userId
     * @return     : int
     */
    User selectByPrimaryKey(Integer userId);

    /**
     *  更新用户
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 10:00
     * @param       user
     * @return     : int
     */
    int updateByPrimaryKeySelective(User user);

    /**
     *  更新用户
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 10:02
     * @param       user
     * @return     : int
     */
    int updateByPrimaryKey(User user);


    /**
     * 通过用户名查找用户
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/18 19:08
     * @param       userName
     * @return     : com.fpms.entity.User
     */
    User selectByUserName(String userName);
}