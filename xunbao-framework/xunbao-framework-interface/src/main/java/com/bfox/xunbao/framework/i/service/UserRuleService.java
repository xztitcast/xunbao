package com.bfox.xunbao.framework.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.framework.entity.UserRule;
import com.bfox.xunbao.framework.i.service.support.ActivityRuleExtendService;

/**
 * <p>
 * 活动用户规则表 服务类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
public interface UserRuleService extends FrameworkService<UserRule, Long>, ActivityRuleExtendService {

}
