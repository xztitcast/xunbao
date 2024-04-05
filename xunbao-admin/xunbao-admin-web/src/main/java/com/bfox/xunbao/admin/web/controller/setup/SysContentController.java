package com.bfox.xunbao.admin.web.controller.setup;

import com.bfox.xunbao.admin.web.controller.BaseController;
import com.bfox.xunbao.admin.web.modelAndView.view.TreeNodeView;
import com.bfox.xunbao.admin.web.modelAndView.view.TreeView;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.setup.entity.ContentCat;
import com.bfox.xunbao.setup.i.service.ContentCatService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/content")
public class SysContentController extends BaseController {

    @DubboReference
    private ContentCatService contentCatService;

    @GetMapping("/cat")
    public R list(@RequestParam Long parentId) {
        if(parentId == null || parentId < 0) {
            parentId = 0L;
        }
        List<ContentCat> list = this.contentCatService.getContentCatList(parentId, getTenantId());
        List<TreeView<Long>> result = list.stream().map(e -> {
            List<ContentCat> contentCatList = this.contentCatService.getContentCatList(e.getId(), getTenantId());
            TreeView<Long> view = new TreeView<>(e.getName(), e.getId(), !contentCatList.isEmpty());
            return view;
        }).collect(Collectors.toList());
        return R.ok(result);
    }
}
