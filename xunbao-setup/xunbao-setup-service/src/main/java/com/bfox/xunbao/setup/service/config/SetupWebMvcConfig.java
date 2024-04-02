package com.bfox.xunbao.setup.service.config;

import com.bfox.xunbao.common.security.resolver.LoginAuthenticationMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 设置模块MVC配置
 */
@Configuration
public class SetupWebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private LoginAuthenticationMethodArgumentResolver loginAuthenticationMethodArgumentResolver;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(loginAuthenticationMethodArgumentResolver);
	}
	
}