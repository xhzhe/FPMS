package com.fpms.controller;

import com.fpms.entity.User;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
     * @date       ：Created in 2019/6/19 14:05
     * @param       user
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PostMapping(value = "/user/actions/register")
    public ResultBean<Boolean> register( @RequestBody User user){
        ResultBean<Boolean> resultBean = new ResultBean<>();
        try{
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
        User user = userService.getUserById(userId);
        ResultBean<User> resultBean = new ResultBean<>();
        if(user == null){
            resultBean.setState(1);
            resultBean.setMsg("该用户不存在");
        }else {
            resultBean= new ResultBean<>(user);
        }
        return resultBean;
    }


    @PostMapping(value = "/user/{userId}/userPwd/actions/modify")
    public ResultBean<User> modifyPassword(@RequestBody User user,@PathVariable Integer userId){
//        User user = userService.getUserById(userId);
        System.out.println(user.getUserPwd());
        return null;
    }
}
