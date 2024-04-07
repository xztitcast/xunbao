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
    public R list() {
        List<ContentCat> list = this.contentCatService.getContentCatList(getTenantId());
        List<TreeNodeView<Long>> result = resolveTreeList(list);
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

    /**
     * 解析树
     * @param list
     * @return
     */
    private List<TreeNodeView<Long>> resolveTreeList(List<ContentCat> list) {
        return list.stream().filter(e -> e.getParentId() == 0L).map(e -> {
            TreeNodeView<Long> view = new TreeNodeView<>(e.getName(), e.getId(), e.getParentId(), null);
            return findTreeNode(list, view);
        }).collect(Collectors.toList());
    }

    /**
     * 查找树下子节点
     * @param list
     * @param view
     * @return
     */
    private TreeNodeView<Long> findTreeNode(List<ContentCat> list, TreeNodeView<Long> view) {
        list.stream().filter(e -> e.getParentId() == view.getId().longValue()).forEach(e -> {
            TreeNodeView<Long> nodeView = new TreeNodeView<>(e.getName(), e.getId(), e.getParentId(), null);
            view.getChildren().add(findTreeNode(list, nodeView));
        });
        return view;
    }

}
