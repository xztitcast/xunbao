package com.bfox.xunbao.framework.model;


import com.bfox.xunbao.framework.entity.ActivityRule;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 活动规则
 * @Author Eden
 * @Date 2025/3/13 23:58
 */
@Getter
@Setter
public class SysActivityRuleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @NotNull(message = "活动ID不能为空")
    private Long id;

    /**
     * 用户规则
     */
    private ActivityRule userRule;

    /**
     * 周期规则
     */
    private ActivityRule cycleRule;

    /**
     * 星级规则
     */
    private ActivityRule starRule;
}
