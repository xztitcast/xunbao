package com.bfox.xunbao.common.security.session;

import com.bfox.xunbao.common.core.S;
import com.bfox.xunbao.common.core.exception.custom.AuthenticationException;
import com.bfox.xunbao.common.core.injecter.Principal;
import lombok.Setter;

/**
 * 本地缓存管理器
 * @author eden
 * @date 2024/7/27 21:19:19
 */
public final class LocalCacheManager {

    @Setter
    private static TokenSessionManager tokenSessionManager;

    /**
     * 根据token获取缓存用户信息
     * @param token
     * @return
     */
    public static Principal getByToken(String token) {
        Principal principal = tokenSessionManager.getByToken(token);
        if(principal == null) {
            throw new AuthenticationException(S.UNAUTHORIZED.getValue(), S.UNAUTHORIZED.getMessage());
        }
        return principal;
    }

    /**
     * 根据用户ID获取缓存用户信息
     * @param userId
     * @return
     */
    public static Principal getByUserId(Long userId){
        Principal principal = tokenSessionManager.getByUerId(userId);
        if(principal == null) {
            throw new AuthenticationException(S.UNAUTHORIZED.getValue(), S.UNAUTHORIZED.getMessage());
        }
        return principal;
    }
}
