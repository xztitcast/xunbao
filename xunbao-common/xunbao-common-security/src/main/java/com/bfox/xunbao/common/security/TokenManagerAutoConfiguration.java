package com.bfox.xunbao.common.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.bfox.xunbao.common.security.resolver.LoginAuthenticationMethodArgumentResolver;
import com.bfox.xunbao.common.security.session.AuthenticationTokenWebManager;
import com.bfox.xunbao.common.security.session.DefaultAuthenticationTokenWebManager;

/**
 * Token自动配置类
 * @author eden
 * @date 2023/9/23 22:34:34
 */
@Configuration
@ConditionalOnClass(RedisProperties.class)
public class TokenManagerAutoConfiguration {

    @Bean("loginAuthenticationMethodArgumentResolver")
    LoginAuthenticationMethodArgumentResolver resolver(AuthenticationTokenWebManager manager) {
        return new LoginAuthenticationMethodArgumentResolver(manager);
    }

    @Bean("authenticationTokenWebManager")
    AuthenticationTokenWebManager manager(RedisTemplate<String, String> redisTemplate) {
        return new DefaultAuthenticationTokenWebManager(redisTemplate);
    }
}
