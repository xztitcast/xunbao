package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.admin.web.modelAndView.view.SelectorView;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.S;
import com.bfox.xunbao.framework.entity.Star;
import com.bfox.xunbao.framework.i.service.StarService;
import com.bfox.xunbao.framework.model.CommonModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台星级控制器
 * @Author Eden
 * @Date 2025/3/5 0:32
 */
@RestController
@RequestMapping("/sys/star")
public class SysStarController {

    @DubboReference
    private StarService starService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:star:list')")
    public R list(CommonModel model) {
        P<Star> p = this.starService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:star:info')")
    public R info(@PathVariable("id") Long id) {
        Star entity = this.starService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存星级数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:star:save')")
    public R save(@RequestBody Star entity) {
        this.starService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改星级数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:star:update')")
    public R update(@RequestBody Star entity) {
        this.starService.updateEntity(entity);
        return R.ok();
    }

    @Log("修改星级状态")
    @PostMapping("/change")
    @PreAuthorize(value = "hasAuthority('sys:star:update')")
    public R change(@RequestBody Star entity) {
        Star star = this.starService.getEntity(entity.getId());
        if(star == null) {
           return R.error("修改星级不存在!");
        }
        if(entity.getStatus() == star.getStatus().intValue()) {
            return R.error(S.USER_STATUS_PARAMETER_ERROR);
        }
        this.starService.updateEntity(entity);
        return R.ok();
    }

    /**
     * 选择器数据
     * @return
     */
    @GetMapping("/select")
    @PreAuthorize(value = "hasAuthority('sys:star:list')")
    public R select() {
        List<Star> list = this.starService.getSelection();
        SelectorView<Long> view = new SelectorView<>();
        List<SelectorView<Long>> result = list.stream().map(item -> new SelectorView<Long>(item.getName(), item.getId())).toList();
        return R.ok(result);
    }
}
