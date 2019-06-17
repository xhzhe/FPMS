package com.fpms.dao;

import com.fpms.entity.Evaluation;

public interface EvaluationDao {
    int deleteByPrimaryKey(Integer evaluationId);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Integer evaluationId);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);
}