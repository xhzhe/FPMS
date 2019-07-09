package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dao.ProductLibraryConfigurationDao;
import com.fpms.entity.ConfigurationReview;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ConfigurationReviewService;
import com.fpms.utils.HtmlUtil;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审逻辑控制器
 * @modified :
 */
@RestController
@CrossOrigin
public class ConfigurationReviewController {
    @Autowired
    private ConfigurationReviewService configurationReviewService;

    @Autowired
    private ProductLibraryConfigurationDao productLibraryConfigurationDao;

    private final String STAFF_ID="staffId";
    /**
     *  新增对配置产品的评估
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:50
     * @param       param
     * @param       productConId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @modifiedby: YongBiao Liao
     */
    @OperationLog(value = "新增配置评估")
    @PostMapping("/productCon/{productConId}/actions/review")
    public ResultBean<Boolean> addConfigurationReview(@RequestBody Map<String,String> param, @PathVariable Integer productConId){
        try{
            if(param.get(STAFF_ID) == null || param.get(STAFF_ID).isEmpty()){
                return new ResultBean<>("未获取到员工id");
            }
            if(param.get("reviewStatus") == null || param.get("reviewStatus").isEmpty()){
                return new ResultBean<>("为获取到要设置的评审状态");
            }
            if(param.get("reviewDesc") == null || param.get("reviewDesc").isEmpty()){
                return new ResultBean<>("评审意见为空！");
            }
            if("0".equals(param.get("reviewStatus"))){
                return new ResultBean<>("不能将评审状态设置为0");
            }
            Integer staffId = Integer.valueOf(param.get("staffId"));
            Byte reviewStatus = Byte.valueOf(param.get("reviewStatus"));
            ConfigurationReview configurationReview = new ConfigurationReview();
            configurationReview.setProductConId(productConId);
            configurationReview.setStaffId(staffId);
            configurationReview.setReviewDesc(HtmlUtil.Html2Text(param.get("reviewDesc")));
            configurationReviewService.addReview(configurationReview);
            ProductLibraryConfiguration productLibraryConfiguration = new ProductLibraryConfiguration();
            productLibraryConfiguration.setProductConId(productConId);
            productLibraryConfiguration.setReviewStatus(reviewStatus);
            productLibraryConfigurationDao.updateByPrimaryKeySelective(productLibraryConfiguration);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  获取配置产品的评估
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/24 11:25
     * @param       productConId
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.entity.ConfigurationReview>
     */
    @GetMapping("/productCon/{productConId}/review")
    public ResultBean<ConfigurationReview> selectReviewByProductConId(@PathVariable Integer productConId){
        ConfigurationReview configurationReview = new ConfigurationReview();
        try{
            configurationReview = configurationReviewService.selectByProductConId(productConId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(configurationReview);
    }
}
