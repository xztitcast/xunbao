package com.bfox.xunbao.admin.web.annotation.aspect;

import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.support.handlers.ObjectFillFacadeHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 填充数据切面
 * @author eden
 * @date 2023年2月19日 下午4:30:33
 */
@Aspect
@Order(1)
@Component
public class StrictFillAspect {

	@Pointcut("@annotation(com.bfox.xunbao.admin.web.annotation.Fill)")
	public void fillPointCut() { 
		
	}
	
	/**
	 * 环绕通知
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("fillPointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Fill fill = method.getAnnotation(Fill.class);
		Object[] args = joinPoint.getArgs();
		Arrays.stream(args).forEach(arg -> ObjectFillFacadeHandler.doFill(arg, fill.value()));
		return joinPoint.proceed();
	}
}
