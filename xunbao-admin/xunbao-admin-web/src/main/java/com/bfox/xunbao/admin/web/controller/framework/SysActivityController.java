package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.S;
import com.bfox.xunbao.framework.entity.Activity;
import com.bfox.xunbao.framework.i.service.ActivityService;
import com.bfox.xunbao.framework.model.SysCommonModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 后台活动控制器
 * @Author Eden
 * @Date 2025/3/11 23:52
 */
@RestController
@RequestMapping("/sys/activity")
public class SysActivityController {

    @DubboReference
    private ActivityService activityService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:activity:list')")
    public R list(SysCommonModel model) {
        P<Activity> p = this.activityService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:activity:info')")
    public R info(@PathVariable("id") Long id) {
        Activity entity = this.activityService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存活动数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:activity:save')")
    public R save(@RequestBody Activity entity) {
        Long id = this.activityService.saveEntity(entity);
        return R.ok(id);
    }

    @Log("修改活动数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:activity:update')")
    public R update(@RequestBody Activity entity) {
        this.activityService.updateEntity(entity);
        return R.ok();
    }

    @Log("删除活动数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:activity:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.activityService.delete(Arrays.asList(ids));
        return R.ok();
    }

    @Log("切换活动状态")
    @PostMapping("/change")
    @PreAuthorize(value = "hasAuthority('sys:activity:update')")
    public R change(@RequestBody Activity entity) {
        Activity activity = this.activityService.getEntity(entity.getId());
        if(activity == null) {
            return R.error("活动不存在!");
        }
        if(entity.getStatus() == activity.getStatus().intValue()) {
            return R.error(S.USER_STATUS_PARAMTER_ERROR);
        }
        this.activityService.updateEntity(entity);
        return R.ok();
    }
}
