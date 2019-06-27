package com.fpms.controller;

import com.fpms.dto.UserRegisterDto;
import com.fpms.entity.User;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:02
 * @description: 用户业务业务逻辑控制器
 * @modified :
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 19:38
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PostMapping(value = "/user/actions/register")
    public ResultBean<Boolean> register(String userName, String userPwd, String userGender, String userBrithday, String userPhone,
                                        String userEmail, String certificateType, String certificateNum, String career){
        ResultBean<Boolean> resultBean = new ResultBean<>();
        try{
            User user = new User();
            user.setUserName(userName);
            user.setUserPwd(userPwd);
            user.setUserGender(userGender);
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
            user.setUserBrithday(formatter.parse(userBrithday));
            user.setUserPhone(userPhone);
            user.setUserEmail(userEmail);
            user.setCertificateType(Byte.valueOf(certificateType));
            user.setCertificateNum(certificateNum);
            user.setCareer(career);
            int result = userService.register(user);
            if(result == 0){
                resultBean.setState(1);
                resultBean.setMsg("用户已存在");
            }else if (result == 1){

            }else if (result == 2){
                resultBean.setState(1);
                resultBean.setMsg("注册失败");
            }
        }catch (Exception e){
            resultBean.setState(1);
            resultBean.setMsg(e.getMessage());
        }
        return resultBean;
    }

    /**
     * 根据用户id获取用户的详细信息
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 14:06
     * @param       userId
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.entity.User>
     */
    @GetMapping(value = "/user/{userId}")
    public ResultBean<User> getUserById(@PathVariable Integer userId){
        try {
            User user = userService.getUserById(userId);
            ResultBean<User> resultBean = new ResultBean<>();
            if (user == null) {
                resultBean.setState(1);
                resultBean.setMsg("该用户不存在");
            } else {
                resultBean = new ResultBean<>(user);
            }
            return resultBean;
        }catch (Exception e){
            return new ResultBean<>(e);
        }
    }

    /**
     *  修改用户密码
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 16:21
     * @param       userPwd
     * @param       userId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PutMapping(value = "/user/{userId}/userPwd/actions/modify")
    public ResultBean<Boolean> modifyUserPwd(@RequestParam("userPwd") String userPwd, @RequestParam("oldUserPwd") String oldUserPwd ,@PathVariable Integer userId){
        try{
            User user = userService.getUserById(userId);
            if(user.getUserPwd().equals(oldUserPwd)) {
                user.setUserPwd(userPwd);
                userService.updateUser(user);
            }else {
                return new ResultBean<>("原密码错误！");
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     * 用户修改信息
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 23:29
     * @param       user
     * @param       userId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PutMapping(value = "/user/{userId}")
    public ResultBean<Boolean> modifyUser(@RequestBody User user, @PathVariable Integer userId){
        try{
            if(userService.getUserById(userId) != null ) {
                userService.updateUser(user);
                return new ResultBean<>();
            }else {
                return new ResultBean<>("此用户不存在");
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
    }

    /**
     * 用户修改支付密码
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 23:59
     * @param       payPwd
     * @param       userId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PutMapping(value = "/user/{userId}/payPwd/actions/modify")
    public ResultBean<Boolean> modifyPayPwd(@RequestParam("payPwd") String payPwd, @PathVariable Integer userId){
        try{
            User user = new User();
            user.setUserId(userId);
            user.setPayPwd(payPwd);
            userService.updateUser(user);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     * 获取用户的支付密码
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/27 14:51
     * @param       userId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.String>
     */
    @GetMapping(value = "/user/{userId}/payPwd")
    public ResultBean<String> getPayPwd(@PathVariable Integer userId){
        try{
            User user = userService.getUserById(userId);
            String payPwd = user.getPayPwd();
            ResultBean<String> resultBean = new ResultBean<>();
            resultBean.setData(payPwd);
            return resultBean;
        }catch (Exception e){
            return new ResultBean<>(e);
        }
    }
}
