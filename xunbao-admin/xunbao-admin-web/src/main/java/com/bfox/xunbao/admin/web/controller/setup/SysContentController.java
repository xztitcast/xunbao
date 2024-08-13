package com.bfox.xunbao.admin.web.controller.setup;

import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.admin.web.controller.BaseController;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.setup.entity.Content;
import com.bfox.xunbao.setup.i.service.ContentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/sys/content")
public class SysContentController extends BaseController {

    @DubboReference
    private ContentService contentService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:content:list')")
    public R list(LimitModel m) {
        P<Content> p = this.contentService.getBaseList(m);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:content:info')")
    public R info(@PathVariable Long id) {
        Content entity = this.contentService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存内容数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:content:save')")
    public R save(@RequestBody Content t) {
        this.contentService.saveEntity(t);
        return R.ok();
    }

    @Log("修改内容数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:content:update')")
    public R update(@RequestBody Content t) {
        this.contentService.updateEntity(t);
        return R.ok();
    }

    @Log("删除内容数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:content:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.contentService.delete(Arrays.asList(ids));
        return R.ok();
    }
}
