package com.fpms.controller;

import com.fpms.dao.StaffDao;
import com.fpms.dto.StaffDto;
import com.fpms.dto.UserDto;
import com.fpms.entity.Staff;
import com.fpms.entity.User;
import com.fpms.enums.LoginResultEnum;
import com.fpms.service.LoginService;
import com.fpms.utils.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:59
 * @description: 登录控制器类
 * @modified :
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/user/actions/login")
    public HashMap<String,Object> loginByUser(HttpServletRequest request){
        HashMap<String, Object> resultMap = new HashMap<>(16);
        String userName = HttpServletRequestUtil.getString(request,"user_name");
        String password = HttpServletRequestUtil.getString(request,"password");
        try {
            HashMap<String, Object> loginResult = loginService.loginByUser(userName, password);
            if (Integer.valueOf(loginResult.get("code").toString()) == LoginResultEnum.SUCCESS.getCode().intValue()) {
                resultMap.put("success", true);

                User user = (User) loginResult.get("user");
                UserDto userDto = new UserDto();
                userDto.setUserId(user.getUserId());
                userDto.setUserName(user.getUserName());
                userDto.setUserAddress(user.getUserAddress());
                userDto.setUserEmail(user.getUserEmail());
                userDto.setUserGender(user.getUserGender());
                userDto.setUserPhone(user.getUserPhone());

                request.getSession().setAttribute("user",user);
                //将user返回给前端之后需要使用
                resultMap.put("user_dto", userDto);
            } else {
                resultMap.put("success", false);
            }
            resultMap.put("code", loginResult.get("code"));
            resultMap.put("msg", loginResult.get("msg"));

        } catch (Exception ex) {
            resultMap.put("success", false);
            resultMap.put("code", "-1");
            resultMap.put("msg", ex.getMessage());
        }
        return resultMap;
    }

    @PostMapping(value = "/staff/actions/login")
    public HashMap<String,Object> loginByStaff(HttpServletRequest request){
        HashMap<String, Object> resultMap = new HashMap<>(16);
        String staffName = HttpServletRequestUtil.getString(request,"staff_name");
        String staffPwd = HttpServletRequestUtil.getString(request,"staff_pwd");
        try {
            HashMap<String, Object> loginResult = loginService.loginByStaff(staffName, staffPwd);
            if (Integer.valueOf(loginResult.get("code").toString()) == LoginResultEnum.SUCCESS.getCode().intValue()) {
                resultMap.put("success", true);

                Staff staff = (Staff) loginResult.get("staff");
                StaffDto staffDto = new StaffDto();
                staffDto.setStaffId(staff.getStaffId());
                staffDto.setStaffName(staff.getStaffName());
                staffDto.setStaffGender(staff.getStaffGender());
                staffDto.setStaffPhone(staff.getStaffPhone());
                staffDto.setStaffStatus(staff.getStaffStatus());

                request.getSession().setAttribute("staff",staff);
                //将user返回给前端之后需要使用
                resultMap.put("staff_dto", staffDto);
            } else {
                resultMap.put("success", false);
            }
            resultMap.put("code", loginResult.get("code"));
            resultMap.put("msg", loginResult.get("msg"));

        } catch (Exception ex) {
            resultMap.put("success", false);
            resultMap.put("code", "-1");
            resultMap.put("msg", ex.getMessage());
        }
        return resultMap;
    }

//    @RequestMapping(value = "/index")
//    public HashMap<String,Object> index(){
//        return loginService.loginByStaff("honghong","aa123456");
//    }
}
