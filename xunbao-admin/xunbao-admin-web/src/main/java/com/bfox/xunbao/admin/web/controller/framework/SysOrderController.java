package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.admin.web.controller.BaseController;
import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.enums.BaseEnum;
import com.bfox.xunbao.framework.entity.Balance;
import com.bfox.xunbao.framework.entity.Order;
import com.bfox.xunbao.framework.i.service.BalanceService;
import com.bfox.xunbao.framework.i.service.OrderService;
import com.bfox.xunbao.framework.model.OrderModel;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * 任务订单控制器
 * @Author Eden
 * @Date 2025/3/1 15:25
 */
@RestController
@RequestMapping("/sys/order")
public class SysOrderController extends BaseController {

    @DubboReference
    private OrderService orderService;

    @DubboReference
    private BalanceService balanceService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:order:list')")
    public R list(OrderModel model) {
        model.setCreator(getUserId());
        P<Order> p = this.orderService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:order:info')")
    public R info(@PathVariable("id") Long id) {
        Order entity = this.orderService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存任务订单数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:order:save')")
    public R save(@RequestBody Order entity) {
        entity.setStatus(BaseEnum.TWO);
        this.orderService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改任务订单数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:order:update')")
    public R update(@RequestBody Order entity) {
        entity.setStatus(BaseEnum.TWO);
        entity.setSerialNumber(null);
        this.orderService.updateEntity(entity);
        return R.ok();
    }

    @Log("删除任务订单数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:order:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.orderService.delete(Arrays.asList(ids));
        return R.ok();
    }

    @Log("发布任务订单")
    @PostMapping("/change")
    @PreAuthorize(value = "hasAuthority('sys:order:update')")
    public R change(@RequestBody Order entity) {
        Order order = this.orderService.getEntity(entity.getId());
        if(order == null) {
            return R.error("订单不存在!");
        }
        if(entity.getStatus() != BaseEnum.TWO) {
            return R.error("非审核成功状态不允许发布!");
        }
        SysUser sysUser = getUser();
        if(StringUtils.isBlank(sysUser.getAccId())) {
            return R.error("请先关联小程序账号");
        }
        Balance balance = this.balanceService.getBalance(sysUser.getAccId());
        if(balance == null || balance.getAmount().compareTo(new BigDecimal("1000")) < 0) {
            return R.error("保证金余额不足,请在小程序充值!");
        }
        entity.setStatus(BaseEnum.THREE);
        entity.setPublishTime(new Date());
        this.orderService.updateEntity(entity);
        return R.ok();
    }
}
