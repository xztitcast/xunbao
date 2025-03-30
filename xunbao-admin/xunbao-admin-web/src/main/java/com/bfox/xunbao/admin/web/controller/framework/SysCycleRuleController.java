package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.entity.CycleRule;
import com.bfox.xunbao.framework.i.service.CycleRuleService;
import com.bfox.xunbao.framework.model.CommonModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 后台管理周期规则控制器
 * @Author Eden
 * @Date 2025/3/16 14:51
 */
@RestController
@RequestMapping("/sys/cycle/rule")
public class SysCycleRuleController {

    @DubboReference
    private CycleRuleService cycleRuleService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:cycle:rule:list')")
    public R list(CommonModel model) {
        P<CycleRule> p = this.cycleRuleService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:cycle:rule:info')")
    public R info(@PathVariable("id") Long id) {
        CycleRule entity = this.cycleRuleService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存周期规则数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:cycle:rule:save')")
    public R save(@RequestBody CycleRule entity) {
        this.cycleRuleService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改周期规则数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:cycle:rule:update')")
    public R update(@RequestBody CycleRule entity) {
        this.cycleRuleService.updateEntity(entity);
        return R.ok();
    }

    @Log("删除周期规则数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:cycle:rule:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.cycleRuleService.delete(Arrays.asList(ids));
        return R.ok();
    }
}
