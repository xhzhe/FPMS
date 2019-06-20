package com.fpms.service;

import com.fpms.entity.LogOperate;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/19 21:31
 * @description:
 * @modified :
 */
public interface LogOperateService {

    /**
     *  返回所有操作日志
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:38
     * @param
     * @return     : java.util.List<com.fpms.entity.LogOperate>
     */
    List<LogOperate> selectAllLogOperate();

    /**
     *  添加一条操作记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 22:58
     * @param       logOperate
     * @return     : void
     */
    void addLogOperate(LogOperate logOperate);
}
