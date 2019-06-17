package com.fpms.service;

import java.awt.*;
import java.lang.annotation.Documented;

/**
 * @author     : YongBiao Liao
 * @date       : 2019/6/14 14:35
 * @description:
 * @modified   :
 */
public interface LoginService {
    /**
     * 管理员登录
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/14 16:29
     * @param       systemAdmName
     * @param       systemAdmPwd
     * @return     : void
     */
    void loginBySystemAdministrator(String systemAdmName, String systemAdmPwd);
}
