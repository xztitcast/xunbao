package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.entity.Balance;
import com.bfox.xunbao.framework.i.service.BalanceService;
import com.bfox.xunbao.framework.model.SysBalanceModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 后台保证金控制器
 * @Author Eden
 * @Date 2025/3/5 0:25
 */
@RestController
@RequestMapping("/sys/balance")
public class SysBalanceController {

    @DubboReference
    private BalanceService balanceService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:balance:list')")
    public R list(SysBalanceModel model) {
        P<Balance> p = this.balanceService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:balance:info')")
    public R info(@PathVariable("id") Long id) {
        Balance entity = this.balanceService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存保证金数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:balance:save')")
    public R save(@RequestBody Balance entity) {
        this.balanceService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改保证金数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:balance:update')")
    public R update(@RequestBody Balance entity) {
        this.balanceService.updateEntity(entity);
        return R.ok();
    }

}
