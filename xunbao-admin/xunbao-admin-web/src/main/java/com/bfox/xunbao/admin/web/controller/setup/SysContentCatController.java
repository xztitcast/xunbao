package com.bfox.xunbao.admin.web.controller.setup;

import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.admin.web.controller.BaseController;
import com.bfox.xunbao.admin.web.modelAndView.view.TreeNodeView;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.setup.entity.ContentCat;
import com.bfox.xunbao.setup.i.service.ContentCatService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/content/cat")
public class SysContentCatController extends BaseController {

    @DubboReference
    private ContentCatService contentCatService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:content:cat:list')")
    public R list(@RequestParam Long parentId) {
        if(parentId == null || parentId < 0) {
            parentId = 0L;
        }
        List<ContentCat> list = this.contentCatService.getContentCatList(parentId, getTenantId());
        List<TreeNodeView<Long>> result = list.stream().map(e -> {
            List<ContentCat> contentCatList = this.contentCatService.getContentCatList(e.getId(), getTenantId());
            TreeNodeView<Long> view = new TreeNodeView<>(e.getName(), e.getId(), !contentCatList.isEmpty(), e.getParentId(),null);
            return view;
        }).collect(Collectors.toList());
        return R.ok(result);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:content:cat:info')")
    public R info(@PathVariable Long id) {
        ContentCat entity = this.contentCatService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存内容分类数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:content:cat:save')")
    public R save(@RequestBody ContentCat t) {
        this.contentCatService.saveEntity(t);
        return R.ok();
    }

    @Log("修改内容分类数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:content:cat:update')")
    public R update(ContentCat t) {
        this.contentCatService.updateEntity(t);
        return R.ok();
    }

    @Log("删除内容分类数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:content:cat:delete')")
    public R delete(@RequestBody Long[] ids) {
        this.contentCatService.delete(Arrays.asList(ids));
        return R.ok();
    }

}
