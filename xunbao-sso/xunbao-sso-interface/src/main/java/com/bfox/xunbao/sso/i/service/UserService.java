package com.bfox.xunbao.sso.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.sso.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Eden
 * @since 2025-03-24 23:01:11
 */
public interface UserService extends FrameworkService<User, String> {

    /**
     * 根据用户名
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 根据微信unionId
     * @param unionId
     * @return
     */
    User getByUnionId(String unionId);

    /**
     * 根据uuid
     * @param uuid
     * @return
     */
    User getByUuid(String uuid);

    /**
     * 根据微信openId
     * @param openId
     * @return
     */
    User getByOpenId(String openId);
}
