package com.fpms.service;

import org.apache.ibatis.annotations.Insert;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

import java.awt.*;
import java.lang.annotation.Documented;
import java.util.HashMap;

/**
 * @author     : YongBiao Liao
 * @date       : 2019/6/14 14:35
 * @description: 登录业务逻辑接口
 * @modified   :
 */
public interface LoginService {

    /**
     * 用户登录
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/18 18:36
     * @param       userName
     * @param       password
     * @return     : java.util.HashMap<java.lang.String,java.lang.Object>
     */
    HashMap<String,Object> loginByUser(String userName, String password);


    /**
     * 职工登录
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/18 20:30
     * @param       staffName
     * @param       staffPwd
     * @return     : java.util.HashMap<java.lang.String,java.lang.Object>
     */
    HashMap<String,Object> loginByStaff(String staffName, String staffPwd);
}
