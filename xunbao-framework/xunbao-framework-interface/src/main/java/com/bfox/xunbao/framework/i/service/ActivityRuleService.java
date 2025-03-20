package com.bfox.xunbao.framework.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.framework.entity.ActivityRule;
import com.bfox.xunbao.framework.view.RuleView;

import java.util.List;

/**
 * <p>
 * 活动规则关联表 服务类
 * </p>
 *
 * @author Eden
 * @since 2025-03-16 15:47:09
 */
public interface ActivityRuleService extends FrameworkService<ActivityRule, Long> {

    /**
     * 获取动态活动规则列表
     * @return
     */
    List<RuleView> getDynamicSelection();

    /**
     * 获取活动规则列表
     * @param activityId
     * @return
     */
    List<RuleView> getInfo(Long activityId);
}
