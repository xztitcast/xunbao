package com.bfox.xunbao.admin.web.support;

import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.service.impl.UserDetailsServiceImpl.LoginUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 认证管理器
 * @author eden
 * @date 2024/8/13 21:40:40
 */
public final class SecuritySubjectManager {

    /**
     * 获取管理系统用户
     * @return
     */
    public static SysUser getSubject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        return loginUserDetails.getSysUser();
    }
}
