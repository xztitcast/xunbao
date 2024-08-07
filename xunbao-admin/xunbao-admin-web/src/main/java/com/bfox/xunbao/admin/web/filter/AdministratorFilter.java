package com.bfox.xunbao.admin.web.filter;

import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.service.impl.UserDetailsServiceImpl.LoginUserDetails;
import com.bfox.xunbao.common.core.Constant;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.S;
import com.bfox.xunbao.common.core.kit.PathMatcherHelper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * 超管权限过滤
 * @author eden
 * @date 2023/7/4 21:41:41
 */
public class AdministratorFilter extends OncePerRequestFilter {

    private static final Set<String> SYSTEM_URL = Set.of("/sys/login", "/sys/logout", "/sys/user/**", "/sys/menu/**", "/sys/role/**", "/sys/log/**", "/sys/dict/**", "/sys/tenant/**", "/sys/dynamic/**", "/sys/region/select");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            LoginUserDetails userDetails = (LoginUserDetails) principal;
            SysUser sysUser = userDetails.getSysUser();
            if(sysUser.getId() == Constant.Sys.SUPER_ADMIN) {
                boolean match = PathMatcherHelper.isMatch(SYSTEM_URL, request.getRequestURI());
                if(!match) {
                    response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.getWriter().print(R.error(S.RESTRICT_ACCESS));
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
