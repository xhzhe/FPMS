package com.fpms.service;

import com.fpms.entity.User;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:58
 * @description:
 * @modified :
 */
public interface UserService {
    /**
     * 用户注册，返回0表示用户已存在，返回1表示成功注册,返回2表示失败
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 9:49
     * @param       user
     * @return     : int
     */
     int register(User user);

    /**
     * 用户注册，返回0表示用户已存在，返回1表示成功注册,返回2表示失败
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 9:49
     * @param       userId
     * @return     : com.fpms.entity.User
     */
     User getUserById(Integer userId);
}
