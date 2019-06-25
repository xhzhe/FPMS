package com.fpms.controller;

import com.fpms.entity.LogMoney;
import com.fpms.entity.ResultBean;
import com.fpms.service.LogMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/25 10:50
 * @description:
 * @modified :
 */
@RestController
@CrossOrigin
public class LogMoneyController {
    @Autowired
    private LogMoneyService logMoneyService;

    @GetMapping("/moneyLogs")
    public ResultBean<List<LogMoney>> selectAllMoneyLogs(){
        List<LogMoney> logMoneyList = new ArrayList<>();
        try{
            logMoneyList = logMoneyService.selectAllLogMoney();
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(logMoneyList);
    }
}
