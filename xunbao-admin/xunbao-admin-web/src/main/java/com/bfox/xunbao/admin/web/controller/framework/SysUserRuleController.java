package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.entity.UserRule;
import com.bfox.xunbao.framework.i.service.UserRuleService;
import com.bfox.xunbao.framework.model.CommonModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 后台管理新用户规则控制器
 * @Author Eden
 * @Date 2025/3/16 15:13
 */
@RestController
@RequestMapping("/sys/user/rule")
public class SysUserRuleController {

    @DubboReference
    private UserRuleService userRuleService;
    
    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:user:rule:list')")
    public R list(CommonModel model) {
        P<UserRule> p = this.userRuleService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:user:rule:info')")
    public R info(@PathVariable("id") Long id) {
        UserRule entity = this.userRuleService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存新用户规则数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:user:rule:save')")
    public R save(@RequestBody UserRule entity) {
        this.userRuleService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改新用户规则数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:user:rule:update')")
    public R update(@RequestBody UserRule entity) {
        this.userRuleService.updateEntity(entity);
        return R.ok();
    }

    @Log("删除新用户规则数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:user:rule:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.userRuleService.delete(Arrays.asList(ids));
        return R.ok();
    }
}
