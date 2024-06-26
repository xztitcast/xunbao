package com.bfox.xunbao.admin.web.global;

import com.bfox.xunbao.common.core.S;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.exception.ControllerExceptionHandler;
import com.bfox.xunbao.common.core.exception.custom.SysServiceException;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统模块全局异常捕获
 * @author eden
 * @date 2022年10月23日 上午10:13:34
 */
@Slf4j
@RestControllerAdvice
public class SysControllerExceptionHandler extends ControllerExceptionHandler {
	
	/**
	 * 系统业务异常捕获
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SysServiceException.class)
	public R sysServiceException(SysServiceException e) {
		log.error("SysServiceException 业务异常", e);
		return R.error(e.getMessage());
	}

	/**
	 * 注解权限异常捕获
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public R accessDeniedException(AccessDeniedException e) {
		log.error("注解PreAuthorize权限认证异常", e);
		return R.error(S.SYSTEM_UNAUTHORIZED);
	}
}
