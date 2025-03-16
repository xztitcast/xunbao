package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.framework.entity.ActivityRule;
import com.bfox.xunbao.framework.i.service.ActivityRuleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 活动规则控制器
 * @Author Eden
 * @Date 2025/3/13 23:57
 */
@RestController
@RequestMapping("/sys/activity/rule")
public class SysActivityRuleController {

    @DubboReference
    private ActivityRuleService activityRuleService;

    /**
     * 新增活动规则
     * @param rules
     */
    @Log("保存活动规则关联数据")
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:activity:save')")
    public void save(@RequestBody ActivityRule[] rules) {
        for(ActivityRule rule : rules) {
            if(rule.getRuleId() != null) {
                rule.setCreated(new Date());
                rule.setUpdated(new Date());
                this.activityRuleService.saveEntity(rule);
            }
        }
    }

    @Log("修改活动规则关联数据")
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:activity:update')")
    public void update(@RequestBody ActivityRule[] rules) {
        for(ActivityRule rule : rules) {
            if(rule.getId() == null) {
                continue;
            }
            if(rule.getRuleId() == null) {
                this.activityRuleService.delete(rule.getId());
            }else {
                rule.setUpdated(new Date());
                this.activityRuleService.updateEntity(rule);
            }
        }
    }
}
