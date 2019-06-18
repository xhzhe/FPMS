package com.fpms.service.impl;

import com.fpms.dao.StaffDao;
import com.fpms.dao.UserDao;
import com.fpms.entity.Staff;
import com.fpms.entity.User;
import com.fpms.enums.LoginResultEnum;
import com.fpms.service.LoginService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:08
 * @description: 登录业务逻辑实现类
 * @modified :
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StaffDao staffDao;
    /**
     * 用户登录
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/18 18:36
     * @param       userName
     * @param       password
     * @return     : java.util.HashMap<java.lang.String,java.lang.Object>
     */
    @Override
    public HashMap<String,Object> loginByUser(String userName, String password) {
        HashMap<String,Object> userLoginResult = new HashMap<>(16);
        if(userName == null || password == null){
            userLoginResult.put("code",LoginResultEnum.INPUT_NULL.getCode());
            userLoginResult.put("msg",LoginResultEnum.INPUT_NULL.getMsg());
            return userLoginResult;
        }
        try {
            User user = userDao.selectByUserName(userName);
            //用户是否存在
            if (user == null){
                userLoginResult.put("code",LoginResultEnum.USER_NOT_EXIT.getCode());
                userLoginResult.put("msg",LoginResultEnum.USER_NOT_EXIT.getMsg());
            } else if (null != user.getUserPwd() && !user.getUserPwd().equals(password)){
                //用户名密码是否一致
                userLoginResult.put("code",LoginResultEnum.NOT_MATCH.getCode());
                userLoginResult.put("msg",LoginResultEnum.NOT_MATCH.getMsg());
            } else{
                userLoginResult.put("code",LoginResultEnum.SUCCESS.getCode());
                userLoginResult.put("msg",LoginResultEnum.SUCCESS.getMsg());
                userLoginResult.put("user",user);
            }
            return userLoginResult;
        }catch (Exception e){

        }
        return userLoginResult;
    }

    /**
     * 职工登录
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/18 20:32
     * @param       staffName
     * @param       staffPwd
     * @return     : java.util.HashMap<java.lang.String,java.lang.Object>
     */
    @Override
    public HashMap<String,Object> loginByStaff(String staffName, String staffPwd) {
        HashMap<String,Object> staffLoginResult = new HashMap<>(15);
        if(staffName == null || staffPwd == null){
            staffLoginResult.put("code",LoginResultEnum.INPUT_NULL.getCode());
            staffLoginResult.put("msg",LoginResultEnum.INPUT_NULL.getMsg());
            return staffLoginResult;
        }
        try {
            Staff staff = staffDao.selectByStaffName(staffName);
            //用户是否存在
            if (staff == null){
                staffLoginResult.put("code",LoginResultEnum.USER_NOT_EXIT.getCode());
                staffLoginResult.put("msg",LoginResultEnum.USER_NOT_EXIT.getMsg());
            } else if (null != staff.getStaffPwd() && !staff.getStaffPwd().equals(staffPwd)){
                //用户名密码是否一致
                staffLoginResult.put("code",LoginResultEnum.NOT_MATCH.getCode());
                staffLoginResult.put("msg",LoginResultEnum.NOT_MATCH.getMsg());
            } else{
                staffLoginResult.put("code",LoginResultEnum.SUCCESS.getCode());
                staffLoginResult.put("msg",LoginResultEnum.SUCCESS.getMsg());
                staffLoginResult.put("staff",staff);
            }
            return staffLoginResult;
        }catch (Exception e){

        }
        return staffLoginResult;
    }
}
