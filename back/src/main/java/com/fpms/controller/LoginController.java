package com.fpms.controller;

import com.fpms.dto.StaffDto;
import com.fpms.dto.UserDto;
import com.fpms.entity.Staff;
import com.fpms.entity.User;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.enums.LoginResultEnum;
import com.fpms.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:59
 * @description: 登录控制器类
 * @modified :
 */
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 14:07
     * @param       request
     * @param       param
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.dto.UserDto>
     */
    @PostMapping(value = "/user/actions/login")
    public ResultBean<UserDto> loginByUser(HttpServletRequest request, @RequestBody Map<String,String> param){
        ResultBean<UserDto> resultBean = new ResultBean<>();
        String userName = param.get("userName");
        String userPwd = param.get("userPwd");
        try {
            HashMap<String, Object> loginResult = loginService.loginByUser(userName, userPwd);
            if (Integer.valueOf(loginResult.get("code").toString()) == LoginResultEnum.SUCCESS.getCode().intValue()) {
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
                resultBean.setData(userDto);
            } else {
                resultBean.setState(1);
                resultBean.setMsg((String) loginResult.get("msg"));
            }

        } catch (Exception ex) {
           resultBean = new ResultBean<>(ex.getMessage());
        }
        return resultBean;
    }

    /**
     * 职工登录
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 14:10
     * @param       request
     * @param       param
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.dto.StaffDto>
     */
    @PostMapping(value = "/staff/actions/login")
    public ResultBean<StaffDto> loginByStaff(HttpServletRequest request,@RequestBody Map<String,String> param){

        ResultBean<StaffDto> resultBean = new ResultBean<>();
        String staffName = param.get("staffName");
        String staffPwd = param.get("staffPwd");
        try {
            HashMap<String, Object> loginResult = loginService.loginByStaff(staffName, staffPwd);
            if (Integer.valueOf(loginResult.get("code").toString()) == LoginResultEnum.SUCCESS.getCode().intValue()) {
                Staff staff = (Staff) loginResult.get("staff");
                StaffDto staffDto = new StaffDto();
                staffDto.setStaffId(staff.getStaffId());
                staffDto.setStaffName(staff.getStaffName());
                staffDto.setStaffGender(staff.getStaffGender());
                staffDto.setStaffPhone(staff.getStaffPhone());
                staffDto.setStaffStatus(staff.getStaffStatus());
                request.getSession().setAttribute("staff",staff);
                //将user返回给前端之后需要使用
                resultBean.setData(staffDto);
            } else {
                resultBean.setState(1);
                resultBean.setMsg((String) loginResult.get("msg"));
            }

        } catch (Exception ex) {
            resultBean = new ResultBean<>(ex.getMessage());
        }
        return resultBean;
    }

    /**
     * 用户注销
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 21:31
     * @param       request
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @GetMapping(value = "/user/logout")
    public ResultBean<Boolean> logoutByUser(HttpServletRequest request){
        try{
            User uer = (User) request.getSession().getAttribute("user");
            if(uer == null){
                return new ResultBean<>("用户未登录");
            }else {
                request.getSession().removeAttribute("user");
                return new ResultBean<>();
            }
        }catch (Exception e){
            return new ResultBean<>(e);
        }
    }

    /**
     * 职工注销
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 21:38
     * @param       request
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @GetMapping(value = "/staff/logout")
    public ResultBean<Boolean> logoutByStaff(HttpServletRequest request){
        try{
            Staff staff = (Staff) request.getSession().getAttribute("staff");
            if(staff == null){
                return new ResultBean<>("用户未登录");
            }else {
                request.getSession().removeAttribute("staff");
                return new ResultBean<>();
            }
        }catch (Exception e){
            return new ResultBean<>(e);
        }
    }
}
