package com.fpms.controller;

import com.fpms.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description:
 * @modified :
 */
@RestController
@CrossOrigin
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;



}
