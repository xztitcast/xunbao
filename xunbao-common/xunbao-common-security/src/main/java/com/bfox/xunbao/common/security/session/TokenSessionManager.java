package com.bfox.xunbao.common.security.session;

import com.bfox.xunbao.common.core.injecter.Principal;

/**
 * @author eden
 * @date 2024/7/27 21:14:14
 */
public interface TokenSessionManager {

    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
    Principal getByToken(String token);

    /**
     * 根据用户ID获取
     * @param userId
     * @return
     */
    Principal getByUerId(Long userId);
}
