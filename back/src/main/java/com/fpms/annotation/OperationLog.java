package com.fpms.annotation;

import java.lang.annotation.*;

/**
 * @author : TianHong Liao
 * @date : 2019/6/19 21:50
 * @description:操作日志注解类
 * @modified :
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface OperationLog {
    String value() default "";
}
