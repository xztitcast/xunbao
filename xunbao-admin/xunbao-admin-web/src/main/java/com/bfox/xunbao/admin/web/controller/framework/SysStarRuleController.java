package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.entity.StarRule;
import com.bfox.xunbao.framework.i.service.StarRuleService;
import com.bfox.xunbao.framework.model.CommonModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 后台管理星级规则控制器
 * @Author Eden
 * @Date 2025/3/16 15:20
 */
@RestController
@RequestMapping("/sys/star/rule")
public class SysStarRuleController {

    @DubboReference
    private StarRuleService starRuleService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:star:rule:list')")
    public R list(CommonModel model) {
        P<StarRule> p = this.starRuleService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:star:rule:info')")
    public R info(@PathVariable("id") Long id) {
        StarRule entity = this.starRuleService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存星级规则数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:star:rule:save')")
    public R save(@RequestBody StarRule entity) {
        this.starRuleService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改星级规则数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:star:rule:update')")
    public R update(@RequestBody StarRule entity) {
        this.starRuleService.updateEntity(entity);
        return R.ok();
    }

    @Log("删除星级规则数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:star:rule:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.starRuleService.delete(Arrays.asList(ids));
        return R.ok();
    }
}
