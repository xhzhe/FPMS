package com.fpms.dao;

import com.fpms.entity.Evaluation;
import org.springframework.stereotype.Component;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface EvaluationDao {
    int deleteByPrimaryKey(Integer evaluationId);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Integer evaluationId);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);
}