package com.bfox.xunbao.admin.web.controller;

import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.sso.entity.Tenant;
import com.bfox.xunbao.sso.i.service.TenantService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 系统管理机构控制器
 * @author eden
 * @date 2024/7/28 21:21:21
 */
@RestController
@RequestMapping("/sys/tenant")
public class SysTenantController extends BaseController {

    @DubboReference
    private TenantService tenantService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:tenant:list')")
    public R list(@RequestParam(required = false, value = "name") String name) {
        List<Tenant> list = this.tenantService.getTenantList(name);
        return R.ok(list);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:tenant:info')")
    public R info(@PathVariable Long id) {
        Tenant entity = this.tenantService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存机构数据")
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:tenant:save')")
    public R save(@RequestBody Tenant tenant) {
        this.tenantService.saveEntity(tenant);
        return R.ok();
    }

    @Log("修改机构数据")
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:tenant:update')")
    public R update(@RequestBody Tenant tenant) {
        this.tenantService.updateEntity(tenant);
        return R.ok();
    }

    @Log("删除机构数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:tenant:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.tenantService.delete(Arrays.asList(ids));
        return R.ok();
    }
}
