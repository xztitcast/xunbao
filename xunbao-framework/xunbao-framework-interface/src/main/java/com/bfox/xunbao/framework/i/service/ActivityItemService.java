package com.bfox.xunbao.framework.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.framework.entity.ActivityItem;

/**
 * <p>
 * 活动奖励表 服务类
 * </p>
 *
 * @author Eden
 * @since 2025-03-16 14:23:28
 */
public interface ActivityItemService extends FrameworkService<ActivityItem, Long> {

    /**
     * 获取活动奖励列表
     * @param activityId
     * @return
     */
    ActivityItem getInfo(Long activityId);
}
