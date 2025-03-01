package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.admin.web.controller.BaseController;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.enums.BaseEnum;
import com.bfox.xunbao.framework.entity.Order;
import com.bfox.xunbao.framework.i.service.OrderService;
import com.bfox.xunbao.framework.model.SysOrderModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:order:list')")
    public R list(SysOrderModel model) {
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
    public R save(Order entity) {
        entity.setStatus(BaseEnum.TWO);
        this.orderService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改任务订单数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:order:update')")
    public R update(Order entity) {
        entity.setStatus(BaseEnum.TWO);
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
}
