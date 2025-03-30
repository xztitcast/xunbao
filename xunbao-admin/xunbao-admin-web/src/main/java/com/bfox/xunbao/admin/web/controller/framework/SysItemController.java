package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.admin.web.modelAndView.view.SelectorView;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.S;
import com.bfox.xunbao.framework.entity.Item;
import com.bfox.xunbao.framework.i.service.ItemService;
import com.bfox.xunbao.framework.model.CommonModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 后台奖励控制器
 * @Author Eden
 * @Date 2025/3/16 12:44
 */
@RestController
@RequestMapping("/sys/item")
public class SysItemController {

    @DubboReference
    private ItemService itemService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:item:list')")
    public R list(CommonModel model) {
        P<Item> p = this.itemService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:item:info')")
    public R info(@PathVariable("id") Long id) {
        Item entity = this.itemService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存奖励数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:item:save')")
    public R save(@RequestBody Item entity) {
        this.itemService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改奖励数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:item:update')")
    public R update(@RequestBody Item entity) {
        this.itemService.updateEntity(entity);
        return R.ok();
    }

    @Log("删除奖励数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:item:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.itemService.delete(Arrays.asList(ids));
        return R.ok();
    }

    @Log("切换奖品状态")
    @PostMapping("/change")
    @PreAuthorize(value = "hasAuthority('sys:item:update')")
    public R change(@RequestBody Item entity) {
        Item item = this.itemService.getEntity(entity.getId());
        if(item == null) {
            return R.error("奖品不存在!");
        }
        if(entity.getStatus() == item.getStatus().intValue()) {
            return R.error(S.USER_STATUS_PARAMETER_ERROR);
        }
        this.itemService.updateEntity(entity);
        return R.ok();
    }

    /**
     * 奖品选择器
     * @return
     */
    @GetMapping("/select")
    @PreAuthorize(value = "hasAuthority('sys:item:list')")
    public R select() {
        List<Item> list = this.itemService.getSelection();
        List<SelectorView<Long>> views = list.stream().map(item -> new SelectorView<Long>(item.getName(), item.getId())).toList();
        return R.ok(views);
    }
}
