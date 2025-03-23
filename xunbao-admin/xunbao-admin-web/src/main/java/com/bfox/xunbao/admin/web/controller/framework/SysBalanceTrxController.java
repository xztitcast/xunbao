package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.entity.BalanceTrx;
import com.bfox.xunbao.framework.i.service.BalanceTrxService;
import com.bfox.xunbao.framework.model.SysBalanceTrxModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 后台管理系统
 * 财务板块订单申诉控制器
 * @Author Eden
 * @Date 2025/3/23 23:26
 */
@RestController
@RequestMapping("/sys/balance-trx")
public class SysBalanceTrxController {

    @DubboReference
    private BalanceTrxService balanceTrxService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:balance-trx:list')")
    public R list(SysBalanceTrxModel model) {
        P<BalanceTrx> p = this.balanceTrxService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:balance-trx:info')")
    public R info(@PathVariable("id") Long id) {
        BalanceTrx entity = this.balanceTrxService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存订单申诉数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:balance-trx:save')")
    public R save(@RequestBody BalanceTrx entity) {
        this.balanceTrxService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改订单申诉数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:balance-trx:update')")
    public R update(@RequestBody BalanceTrx entity) {
        this.balanceTrxService.updateEntity(entity);
        return R.ok();
    }

    @Log("删除订单申诉数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:balance-trx:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.balanceTrxService.delete(Arrays.asList(ids));
        return R.ok();
    }

}
