package com.bfox.xunbao.admin.web.annotation.aspect;


import com.bfox.xunbao.admin.web.annotation.Examine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author Eden
 * @Date 2025/3/1 16:48
 */
@Aspect
@Order(2)
@Component
public class ExamineAspect {

    @Pointcut("@annotation(com.bfox.xunbao.admin.web.annotation.Examine)")
    public void examinePointCut() {

    }

    /**
     * 后置通知
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @After("examinePointCut()")
    public void after(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Examine annotation = method.getAnnotation(Examine.class);

    }
}
