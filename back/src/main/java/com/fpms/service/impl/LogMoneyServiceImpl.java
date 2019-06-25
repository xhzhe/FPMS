package com.fpms.service.impl;

import com.fpms.dao.LogMoneyDao;
import com.fpms.entity.LogMoney;
import com.fpms.service.LogMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/25 10:56
 * @description:
 * @modified :
 */
@Service
public class LogMoneyServiceImpl implements LogMoneyService {
    @Autowired
    private LogMoneyDao logMoneyDao;

    @Override
    public List<LogMoney> selectAllLogMoney() {
        return logMoneyDao.selectAll();
    }

    @Override
    public void addLogMoney(LogMoney logMoney) {
        logMoneyDao.insertSelective(logMoney);
    }
}
