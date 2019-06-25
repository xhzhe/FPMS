package com.fpms.service.impl;

import com.fpms.dao.UserDao;
import com.fpms.entity.User;
import com.fpms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:17
 * @description:
 * @modified :
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户注册，返回0表示用户已存在，返回1表示成功注册,返回2表示失败
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 9:49
     * @param       user
     * @return     : int
     */
    @Override
    public int register(User user) {
        User u = userDao.selectByUserName(user.getUserName());
            if(u == null){
            int number = userDao.insertSelective(user);
            if(number == 1){
                return 1;
            }else {
                return 2;
            }
        }else {
            return 0;
        }
    }

    /**
     * 用户注册，返回0表示用户已存在，返回1表示成功注册,返回2表示失败
     * @author   ：YongBiao Liao
     * @date     ：Created in 2019/6/19 9:49
     * @param      userId
     * @return   : com.fpms.entity.User
     */
    @Override
    public User getUserById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }
}
