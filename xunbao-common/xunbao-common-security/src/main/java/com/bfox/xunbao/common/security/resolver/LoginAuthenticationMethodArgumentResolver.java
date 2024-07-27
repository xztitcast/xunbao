package com.bfox.xunbao.common.security.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bfox.xunbao.common.core.injecter.Principal;
import com.bfox.xunbao.common.core.S;
import com.bfox.xunbao.common.core.exception.custom.AuthenticationException;
import com.bfox.xunbao.common.security.annotation.Subject;
import com.bfox.xunbao.common.security.session.AuthenticationTokenWebManager;


/**
 * 登录验证参数解析器
 * 1.通过网关认证一定能获取到用户ID参数解析成功
 * 2.如果获取用户ID为空，则可以判断用户没有登录或者绕过网关进行攻击
 * @author eden
 * @date 2022年10月17日 下午10:46:51
 */
public class LoginAuthenticationMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private final AuthenticationTokenWebManager authenticationTokenWebManager;

	public LoginAuthenticationMethodArgumentResolver(AuthenticationTokenWebManager authenticationTokenWebManager) {
		this.authenticationTokenWebManager = authenticationTokenWebManager;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Subject.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String authorization = webRequest.getHeader("Authorization");
		Principal principal = this.authenticationTokenWebManager.getByToken(authorization);
		if(principal == null) {
			throw new AuthenticationException(S.UNAUTHORIZED.getValue(), S.UNAUTHORIZED.getMessage());
		}
		Subject subject = parameter.getParameterAnnotation(Subject.class);
		return switch (subject.value()) {
			case ID -> principal.getId();
			case NAME -> principal.getMobile();
			case OPPENID -> principal.getOpenid();
			default -> principal;
		};
	}

}
