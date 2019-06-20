package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.entity.LogOperate;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.LogOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/19 21:30
 * @description:操作日志逻辑控制
 * @modified :
 */
@RestController
@CrossOrigin
public class LogOperateController {
    @Autowired
    private LogOperateService logOperateService;

    @OperationLog(value = "获取所有操作记录")
    @GetMapping("/logOperates")
    public ResultBean<List<LogOperate>> selectAllLogOperate(){
        List<LogOperate> logOperateList = new ArrayList<>();
        try{
            logOperateList = logOperateService.selectAllLogOperate();
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(logOperateList);
    }
}
