package com.bfox.xunbao.admin.web.security;

import com.alibaba.fastjson2.JSON;
import com.bfox.xunbao.admin.web.event.SysLoginEvent;
import com.bfox.xunbao.admin.web.modelAndView.model.LoginModel;
import com.bfox.xunbao.admin.web.modelAndView.view.LoginView;
import com.bfox.xunbao.common.core.Constant.RedisKey;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.utils.IPUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author eden
 * @date 2023/6/25 22:38:38
 */
@Component
public class RefreshAuthenticationSuccessHandler implements AuthenticationSuccessHandler, ApplicationEventPublisherAware {

    @Value("${sys.xunbao.auth:false}")
    private boolean isFactorAuth;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = UUID.randomUUID().toString().replace("-", "");
        if(this.isFactorAuth) {
            this.redisTemplate.opsForValue().set(RedisKey.SYS_SMS_ID_KEY.concat(token), JSON.toJSONString(authentication.getPrincipal()), 5, TimeUnit.MINUTES);
        }else {
            this.redisTemplate.opsForValue().set(RedisKey.SYS_SESSION_ID_KEY.concat(token), JSON.toJSONString(authentication.getPrincipal()), 12, TimeUnit.HOURS);
        }
        String username = request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
        this.applicationEventPublisher.publishEvent(new SysLoginEvent(new LoginModel(username), IPUtil.getIpAddr(request)));
        this.redisTemplate.delete(RedisKey.SYS_LOGIN_LOCKED_KEY.concat(username));
        Long expire = this.redisTemplate.getExpire(RedisKey.SYS_SESSION_ID_KEY.concat(token));
        LoginView view = new LoginView(token, expire, this.isFactorAuth);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().print(R.ok(view));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
