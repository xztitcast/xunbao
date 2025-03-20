package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.entity.ActivityItem;
import com.bfox.xunbao.framework.i.service.ActivityItemService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台活动与奖品关联控制器
 * @Author Eden
 * @Date 2025/3/16 14:33
 */
@RestController
@RequestMapping("/sys/activity/item")
public class SysActivityItemController {

    @DubboReference
    private ActivityItemService activityItemService;

    /**
     * 查询活动下所有关联的奖品
     * @param activityId
     * @return
     */
    @GetMapping("/info")
    @PreAuthorize(value = "hasAuthority('sys:item:info')")
    public R info(@RequestParam("activityId") Long activityId) {
        List<ActivityItem> dataList = this.activityItemService.getDataList(activityId);
        return R.ok(dataList);
    }

    @Log("保存活动与奖品关联数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:item:save')")
    public R save(@RequestBody ActivityItem entity) {
        this.activityItemService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改活动与奖品关联数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:item:update')")
    public R update(@RequestBody ActivityItem entity) {
        this.activityItemService.updateEntity(entity);
        return R.ok();
    }
}
