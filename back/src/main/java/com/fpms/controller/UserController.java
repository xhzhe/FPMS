package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dto.*;
import com.fpms.entity.*;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryConfigurationService;
import com.fpms.service.ProductLibraryPreService;
import com.fpms.service.ProductLibraryStandardService;
import com.fpms.service.UserService;
import com.fpms.utils.EdsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private ProductLibraryStandardService productLibraryStandardService;

    @Autowired
    private ProductLibraryPreService productLibraryPreService;

    @Autowired
    private ProductLibraryConfigurationService productLibraryConfigurationService;

    /**
     * 注册用户
     *
     * @param
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/19 19:38
     */
    @PostMapping(value = "/user/actions/register")
    public ResultBean<Boolean> register(String userName, String userPwd, String userGender, /*String userBrithday,*/ String userPhone,
                                        String userEmail, String certificateType, String certificateNum, String career) {
        ResultBean<Boolean> resultBean = new ResultBean<>();
        try {
            if (userName.length() > 1023 || userEmail.length() > 255
                    || userPhone.length() > 11 || career.length() > 255) {
                return new ResultBean<>("用户名过长！");
            } else if (userEmail.length() > 255) {
                return new ResultBean<>("邮箱过长！");
            } else if (userPhone.length() > 11) {
                return new ResultBean<>("电话过长！");
            } else if (career.length() > 255) {
                return new ResultBean<>("职业过长！");
            } else {
                User user = new User();
                user.setUserName(userName);
                user.setUserPwd(EdsUtil.encryptBasedDes(userPwd));
                user.setUserGender(userGender);
                //SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
                //user.setUserBrithday(formatter.parse(userBrithday));
                user.setUserPhone(userPhone);
                user.setUserEmail(userEmail);
                user.setCertificateType(Byte.valueOf(certificateType));
                user.setCertificateNum(certificateNum);
                user.setCareer(career);
                int result = userService.register(user);
                if (result == 0) {
                    resultBean.setState(1);
                    resultBean.setMsg("用户已存在");
                } else if (result == 1) {

                } else if (result == 2) {
                    resultBean.setState(1);
                    resultBean.setMsg("注册失败");
                }
            }
        } catch (Exception e) {
            resultBean.setState(1);
            resultBean.setMsg(e.getMessage());
        }
        return resultBean;
    }

    /**
     * 根据用户id获取用户的详细信息
     *
     * @param userId
     * @return : com.fpms.entity.pojo.ResultBean<com.fpms.entity.User>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/19 14:06
     */
    @GetMapping(value = "/user/{userId}")
    public ResultBean<User> getUserById(@PathVariable Integer userId) {
        try {
            User user = userService.getUserById(userId);
            ResultBean<User> resultBean = new ResultBean<>();
            if (user == null) {
                resultBean.setState(1);
                resultBean.setMsg("该用户不存在！");
            } else {
                resultBean = new ResultBean<>(user);
            }
            return resultBean;
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     * 修改用户密码
     *
     * @param newUserPwd1
     * @param newUserPwd2
     * @param userId
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/19 16:21
     */
    @OperationLog("用户修改密码")
    @PutMapping(value = "/user/{userId}/userPwd/actions/modify")
    public ResultBean<Boolean> modifyUserPwd(@RequestParam("newUserPwd1") String newUserPwd1, @RequestParam("newUserPwd2") String newUserPwd2,
                                             @RequestParam("oldUserPwd") String oldUserPwd, @PathVariable Integer userId) {
        if(newUserPwd1.equals(newUserPwd2)){
            try {
                User user = userService.getUserById(userId);
                if (EdsUtil.decryptBasedDes(user.getUserPwd()).equals(oldUserPwd)) {
                    user.setUserPwd(EdsUtil.encryptBasedDes(newUserPwd1));
                    userService.updateUser(user);
                } else {
                    return new ResultBean<>("原密码错误！");
                }
            } catch (Exception e) {
                return new ResultBean<>("修改失败！");
            }
            return new ResultBean<>(true);
        }else {
            return new ResultBean<>("两次输入的新密码不一致！请重新输入！");
        }
    }

    /**
     * 用户修改信息
     *
     * @param
     * @param userId
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/19 23:29
     */
    @OperationLog("用户修改信息")
    @PutMapping(value = "/user/{userId}")
    public ResultBean<Boolean> modifyUser(String userEmail, String userPhone,
                                          String userAddress, String career, @PathVariable Integer userId, String gender, String zipCode, String birthDate) {
        try {
            if (userAddress.length() > 1023) {
                return new ResultBean<>("地址过长！");
            } else if (userEmail.length() > 255) {
                return new ResultBean<>("邮箱过长！");
            } else if (zipCode!=null&&zipCode.length() != 6) {
                throw new Exception("邮政编码不为6位");
            } else if (!("m".equals(gender.toLowerCase()) || "f".equals(gender.toLowerCase()))) {
                throw new Exception("性别不正确");
            } else if (userPhone.length() > 11) {
                return new ResultBean<>("电话过长！");
            } else if (career.length() > 255) {
                return new ResultBean<>("职业过长！");
            } else {
                User user = userService.getUserById(userId);
                if (user != null) {
                    user.setUserEmail(userEmail);
                    user.setUserPhone(userPhone);
                    user.setUserAddress(userAddress);
                    user.setCareer(career);
                    user.setUserGender(gender);
                    user.setZipCode(zipCode);
                    if(birthDate!=null) {
                        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = fmt.parse(birthDate);
                        user.setUserBrithday(date);
                    }
                    userService.updateUser(user);
                    return new ResultBean<>(true);
                } else {
                    return new ResultBean<>("此用户不存在！");
                }
            }
        } catch (ParseException e) {
            return new ResultBean<>("日期格式错误");
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     * 用户修改支付密码
     *
     * @param newPayPwd1
     * @param newPayPwd2
     * @param userId
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/19 23:59
     */
    @OperationLog("用户修改支付密码")
    @PutMapping(value = "/user/{userId}/payPwd/actions/modify")
    public ResultBean<Boolean> modifyPayPwd(@RequestParam("newPayPwd1") String newPayPwd1, @RequestParam("newPayPwd2") String newPayPwd2,
                                            @RequestParam("oldPayPwd") String oldPayPwd, @PathVariable Integer userId) {
        if(newPayPwd1.equals(newPayPwd2)){
            try {
                User user = userService.getUserById(userId);
                if (EdsUtil.decryptBasedDes(user.getPayPwd()).equals(oldPayPwd)) {
                    user.setPayPwd(EdsUtil.encryptBasedDes(newPayPwd1));
                    userService.updateUser(user);
                } else {
                    return new ResultBean<>("原支付密码错误！");
                }
            } catch (Exception e) {
                return new ResultBean<>("修改失败");
            }
            return new ResultBean<>(true);
        }else {
            return new ResultBean<>("两次输入的新支付密码不一致！请重新输入！");
        }
    }

    /**
     * 获取用户的支付密码
     *
     * @param userId
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.String>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/27 14:51
     */
    @GetMapping(value = "/user/{userId}/payPwd")
    public ResultBean<String> getPayPwd(@PathVariable Integer userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                return new ResultBean<>("用户不存在！");
            } else {
                String payPwd = EdsUtil.decryptBasedDes(user.getPayPwd());
                ResultBean<String> resultBean = new ResultBean<>();
                resultBean.setData(payPwd);
                return resultBean;
            }
        } catch (Exception e) {
            return new ResultBean<>("获取失败！");
        }
    }

    /**
     * 用户设置支付密码
     *
     * @param userId
     * @param payPwd
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/28 11:23
     */
    @OperationLog("用户设置支付密码")
    @PutMapping(value = "/user/{userId}/payPwd")
    public ResultBean<Boolean> setPayPwd(@PathVariable Integer userId, @RequestParam("payPwd") String payPwd) {
        try {
            User user = new User();
            user.setUserId(userId);
            user.setPayPwd(EdsUtil.encryptBasedDes(payPwd));
            userService.updateUser(user);
        } catch (Exception e) {
            return new ResultBean<>("设置失败！");
        }
        return new ResultBean<>(true);
    }


    @GetMapping("/user/mall")
    public ResultBean<MallDto> getOnSale() throws Exception {
        MallDto mallDto = new MallDto();

        try {
            //获取已上架产品
            ArrayList<ProductDto> productDtoArrayList = new ArrayList<>();
            List<ProductLibraryStandard> productLibraryStandardList = productLibraryStandardService.getProductsOnSale();
            ArrayList<ProductLibraryPre> productLibraryPreArrayList = new ArrayList<>();
            if (productLibraryStandardList != null) {
                for (int i = 0; i < productLibraryStandardList.size(); i++) {
                    ProductDto productDto = new ProductDto();
                    ProductLibraryStandard productLibraryStandard = productLibraryStandardList.get(i);
                    ProductLibraryPre productLibraryPre = productLibraryPreService.selectById(productLibraryStandard.getProductPreId());
                    productLibraryPreArrayList.add(productLibraryPre);
                    productDto.setProductLibraryPre(productLibraryPre);
                    productDto.setProductLibraryStandard(productLibraryStandard);
                    productDtoArrayList.add(productDto);
                }
            }
            mallDto.setProductDtoList(productDtoArrayList);

            //获取已上架配置以及其包含的产品
            ArrayList<ConWithProNameDto> conWithProNameDtoArrayList = new ArrayList<>();
            List<ProductLibraryConfiguration> productLibraryConfigurationList = productLibraryConfigurationService.getConfigurationsOnSale();
            for (int i = 0; i < productLibraryConfigurationList.size(); i++) {
                ConWithProNameDto ConWithProNameDto = new ConWithProNameDto();
                ConWithProNameDto.setProductLibraryConfiguration(productLibraryConfigurationList.get(i));
                List<ProductConfiguration> productConfigurationList = productLibraryConfigurationService.getProductConfigurationByproductConId(productLibraryConfigurationList.get(i).getProductConId());
                if (productConfigurationList != null) {
                    ArrayList<ProductLibraryStandardWithName> productLibraryStandardWithNameArrayList = new ArrayList<>();
                    ArrayList<ProductLibraryPre> productLibraryPreArrayList1 = new ArrayList<>();
                    for (int j = 0; j < productConfigurationList.size(); j++) {
                        ProductLibraryStandardWithName productLibraryStandardWithName = new ProductLibraryStandardWithName();
                        Integer productStdId = productConfigurationList.get(j).getProductStdId();
                        productLibraryStandardWithName.setProductLibraryStandard(productLibraryStandardService.selectById(productStdId));
                        Integer productPreId = productLibraryStandardWithName.getProductLibraryStandard().getProductPreId();
                        ProductLibraryPre productLibraryPre = productLibraryPreService.selectById(productPreId);
                        productLibraryPreArrayList1.add(productLibraryPre);
                        productLibraryStandardWithName.setProductName(productLibraryPre.getProductName());
                        productLibraryStandardWithNameArrayList.add(productLibraryStandardWithName);
                    }
                    ConWithProNameDto.setProductLibraryPreList(productLibraryPreArrayList1);
                    ConWithProNameDto.setProductLibraryStandardWithNameList(productLibraryStandardWithNameArrayList);
                    conWithProNameDtoArrayList.add(ConWithProNameDto);
                } else {
                    continue;
                }
            }
            mallDto.setConWithProNameDtoList(conWithProNameDtoArrayList);
            return new ResultBean<>(mallDto);
        } catch (Exception e) {
            return new ResultBean<>("获取失败！");
        }
    }
}
