package com.fpms.controller;

import com.fpms.dto.StaffDto;
import com.fpms.dto.UserDto;
import com.fpms.entity.Role;
import com.fpms.entity.Staff;
import com.fpms.entity.StaffRole;
import com.fpms.entity.User;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.enums.LoginResultEnum;
import com.fpms.service.LoginService;
import com.fpms.service.RoleService;
import com.fpms.service.StaffRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

    @Autowired
    private StaffRoleService staffRoleService;

    @Autowired
    private RoleService roleService;
    /**
     * 用户登录
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 14:07
     * @param       request
     * @param       userVo
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.dto.UserDto>
     */
    @PostMapping(value = "/user/actions/login")
    public ResultBean<UserDto> loginByUser(HttpServletRequest request, @RequestBody User userVo){
        ResultBean<UserDto> resultBean = new ResultBean<>();
        String userName = userVo.getUserName();
        String userPwd = userVo.getUserPwd();
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
     * @param       staffVo
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.dto.StaffDto>
     */
    @PostMapping(value = "/staff/actions/login")
    public ResultBean<StaffDto> loginByStaff(HttpServletRequest request,@RequestBody Staff staffVo){

        ResultBean<StaffDto> resultBean = new ResultBean<>();
        String staffName = staffVo.getStaffName();
        String staffPwd = staffVo.getStaffPwd();
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

                List<StaffRole> staffRoleList = staffRoleService.selectStaffRoleByStaffId(staff.getStaffId());
                List<Role> roleList = new ArrayList<>();
                for(int i=0;i<staffRoleList.size();i++)
                {
                    Role role = roleService.selectRoleById(staffRoleList.get(i).getRoleId());
                    roleList.add(role);
                }
                staffDto.setRoleList(roleList);
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

}
