package com.bfox.xunbao.admin.web.annotation.aspect;

import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.entity.SysUserTenant;
import com.bfox.xunbao.admin.web.service.impl.UserDetailsServiceImpl.LoginUserDetails;
import com.bfox.xunbao.common.mybatis.entity.BaseEntity;
import com.bfox.xunbao.common.mybatis.entity.CreateEntity;
import com.bfox.xunbao.common.mybatis.entity.TenantEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
		if(fill == null) {
			return joinPoint.proceed();
		}
		FillType value = fill.value();
		Object[] args = joinPoint.getArgs();
		LoginUserDetails loginUserDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		for(Object arg : args) {
			if(arg instanceof BaseEntity && value == FillType.INSERT) {
				setTenantEntityEnhance(arg, loginUserDetails.getSysUserTenant());
			}
			if(arg instanceof CreateEntity && value == FillType.INSERT) {
				setCreateEntityEnhance(arg, loginUserDetails.getSysUser());
			}
			if(value == FillType.UPDATE) {
				setUpdateEntityEnhance(arg, loginUserDetails.getSysUser());
			}
		}
		return joinPoint.proceed();
	}
	
	/**
	 * 填充机构数据
	 * @param arg
	 * @param sysUserTenant
	 */
	private void setTenantEntityEnhance(Object arg, SysUserTenant sysUserTenant) {
		TenantEntity te = (TenantEntity)arg;
		te.setTenantId(sysUserTenant.getTenantId());
		te.setTenantName(sysUserTenant.getTenantName());
	}
	
	/**
	 * 填充创建者数据
	 * @param arg
	 * @param sysUser
	 */
	private void setCreateEntityEnhance(Object arg, SysUser sysUser) {
		CreateEntity ce = (CreateEntity)arg;
		ce.setCreator(sysUser.getId());
		ce.setCreateName(sysUser.getUsername());
		setUpdateEntityEnhance(arg, sysUser);
	}
	
	/**
	 * 填充更新者数据
	 * @param arg
	 * @param sysUser
	 */
	private void setUpdateEntityEnhance(Object arg, SysUser sysUser) {
		CreateEntity ce = (CreateEntity)arg;
		ce.setUpdateName(sysUser.getUsername());
		ce.setUpdater(sysUser.getId());
	}
}
