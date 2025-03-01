package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.admin.web.controller.BaseController;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.entity.OrderWork;
import com.bfox.xunbao.framework.i.service.OrderWorkService;
import com.bfox.xunbao.framework.model.SysOrderWorkModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 任务工作流
 * @Author Eden
 * @Date 2025/3/1 17:11
 */
@RestController
@RequestMapping("/sys/order/work")
public class SysOrderWorkController extends BaseController {

    @DubboReference
    private OrderWorkService orderWorkService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:order:work:list')")
    public R list(SysOrderWorkModel model) {
        model.setSysUserId(getUserId());
        P<OrderWork> p = this.orderWorkService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:order:work:info')")
    public R info(@PathVariable("id") Long id) {
        OrderWork entity = this.orderWorkService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存订单任务流数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:order:work:save')")
    public R save(OrderWork entity) {
        this.orderWorkService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改订单任务流数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:order:work:update')")
    public R update(OrderWork entity) {
        this.orderWorkService.updateEntity(entity);
        return R.ok();
    }

    @Log("删除订单任务流数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:order:work:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.orderWorkService.delete(Arrays.asList(ids));
        return R.ok();
    }

}
