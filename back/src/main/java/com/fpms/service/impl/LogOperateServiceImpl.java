package com.fpms.service.impl;

import com.fpms.dao.LogOperateDao;
import com.fpms.entity.LogOperate;
import com.fpms.service.LogOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/19 21:32
 * @description:
 * @modified :
 */
@Service
public class LogOperateServiceImpl implements LogOperateService {
    @Autowired
    private LogOperateDao logOperateDao;

    @Override
    public List<LogOperate> selectAllLogOperate() {
        return logOperateDao.findAllLogOperate();
    }

    @Override
    public void addLogOperate(LogOperate logOperate) {
        logOperateDao.insertSelective(logOperate);
    }
}
