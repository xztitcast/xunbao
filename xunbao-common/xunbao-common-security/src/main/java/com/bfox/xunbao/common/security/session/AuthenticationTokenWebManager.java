package com.bfox.xunbao.common.security.session;

import com.bfox.xunbao.common.core.injecter.Principal;

/**
 * 认证Token管理器
 * @author eden
 * @date 2023/9/23 22:28:28
 */
public interface AuthenticationTokenWebManager extends TokenSessionManager {

    String TOKEN_KEY = "JC:SECURITY:TOKEN:";

    /**
     * 生成token
     * @param principal (主体)生态用户
     * @return
     */
    String createToken(Principal principal);

    /**
     * 删除
     * @param token 令牌
     * @return
     */
    boolean remove(String token);

    /**
     * 初始化
     */
    void initLocalCache();
}
