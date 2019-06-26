package com.fpms.service.impl;

import com.fpms.dao.EvaluationDao;
import com.fpms.entity.Evaluation;
import com.fpms.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:16
 * @description:
 * @modified :
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationDao evaluationDao;

    @Override
    public Evaluation selectEvaluationByUserIdAndOrderId(Integer userId, Integer orderId) {
         return evaluationDao.selectByUserIdAndOrderId(userId,orderId);
    }

    @Override
    public void updateEvaluationByEvaluationId(Evaluation evaluation) {
        evaluationDao.updateByPrimaryKeySelective(evaluation);
    }

    @Override
    public void delEvaluationByEvaluationId(Integer evaluationId) {
        Evaluation evaluation = new Evaluation();
        evaluation.setEvaluationId(evaluationId);
        evaluation.setIsDelete(Byte.valueOf((byte)1));
        evaluationDao.updateByPrimaryKeySelective(evaluation);
    }

    @Override
    public void addEvaluation(Evaluation evaluation) {
        evaluationDao.insertSelective(evaluation);
    }

    @Override
    public List<Evaluation> selectAllEvaluationByUserId(Integer userId) {
        return evaluationDao.selectAllByUserId(userId);
    }

    @Override
    public List<Evaluation> selectAllEvaluationByProductStdId(Integer productStdId) {
        return evaluationDao.selectAllByProductStdId(productStdId);
    }

    @Override
    public List<Evaluation> selectAllEvaluationByProductConId(Integer productConId) {
        return evaluationDao.selectAllByProductConId(productConId);
    }

    @Override
    public List<Evaluation> selectAll() {
        return evaluationDao.selectAll();
    }
}
