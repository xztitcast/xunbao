package com.bfox.xunbao.framework.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 活动与奖励关联
 * @Author Eden
 * @Date 2025/3/16 13:30
 */
@Getter
@Setter
public class ActivityRewardModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    private Long id;

    /**
     * 奖励ID
     */
    private Long activityId;
}
