package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.entity.ActivityRule;
import com.bfox.xunbao.framework.i.service.ActivityRuleService;
import com.bfox.xunbao.framework.view.RuleView;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
     * 查询活动关联的规则
     * @param activityId
     * @return
     */
    @GetMapping("/info")
    @PreAuthorize(value = "hasAuthority('sys:activity:info')")
    public R info(@RequestParam("activityId") Long activityId) {
        List<RuleView> views = this.activityRuleService.getInfo(activityId);
        return R.ok(views);
    }

    /**
     * 新增活动规则
     * @param rules
     */
    @Log("保存活动规则关联数据")
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:activity:save')")
    public R save(@RequestBody ActivityRule[] rules) {
        for(ActivityRule rule : rules) {
            if(rule.getRuleId() != null) {
                rule.setCreated(new Date());
                rule.setUpdated(new Date());
                this.activityRuleService.saveEntity(rule);
            }
        }
        return R.ok();
    }

    @Log("修改活动规则关联数据")
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:activity:update')")
    public R update(@RequestBody ActivityRule[] rules) {
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
        return R.ok();
    }

    /**
     * 查询规则选择器
     * @return
     */
    @GetMapping("/select")
    @PreAuthorize(value = "hasAuthority('sys:activity:list')")
    public R select() {
        List<RuleView> views = this.activityRuleService.getDynamicSelection();
        return R.ok(views);
    }
}
