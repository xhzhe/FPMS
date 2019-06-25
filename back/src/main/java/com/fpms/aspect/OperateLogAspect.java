package com.fpms.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fpms.annotation.OperationLog;
import com.fpms.entity.LogOperate;
import com.fpms.service.LogOperateService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author : TianHong Liao
 * @date : 2019/6/19 22:37
 * @description:操作日志切面
 * @modified :
 */

public class OperateLogAspect {
    @Autowired
    private LogOperateService logOperateService;

    //切入点
    @Pointcut("@annotation(com.fpms.annotation.OperationLog)")
    public void logPoinCut() {
    }

    @Before("logPoinCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        LogOperate logOperate = new LogOperate();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        OperationLog operationLog= method.getAnnotation(OperationLog.class);

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        // 记录下请求内容
        //TODO 待修改
        logOperate.setStaffId(0);
        logOperate.setOperateUrl(request.getRequestURI());
        logOperate.setOperateDesc(operationLog.value());

        HashMap<String,String> content = new LinkedHashMap<>();
        content.put("HTTP_REQUEST_METHOD", request.getMethod());
        content.put("HTTP_REQUEST_BODY", JSONObject.toJSONString(joinPoint.getArgs()));
        content.put("CLASS_METHOD", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        content.put("ClASS_ARGS", JSON.toJSONString(joinPoint.getArgs()) );
        logOperate.setContent(content.toString());
        TimeZone time = TimeZone.getTimeZone("ETC/GMT-8");
        TimeZone.setDefault(time);
        logOperate.setOperateTime(new Date(System.currentTimeMillis()+ 8*60*60*1000));

        logOperateService.addLogOperate(logOperate);
    }

}

