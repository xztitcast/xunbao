package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.admin.web.annotation.Fill;
import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.S;
import com.bfox.xunbao.framework.entity.Label;
import com.bfox.xunbao.framework.i.service.LabelService;
import com.bfox.xunbao.framework.model.SysStarModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 后台管理标签控制器
 * @Author Eden
 * @Date 2025/3/5 0:41
 */
@RestController
@RequestMapping("/sys/label")
public class SysLabelController {

    @DubboReference
    private LabelService labelService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:label:list')")
    public R list(SysStarModel model) {
        P<Label> p = this.labelService.getBaseList(model);
        return R.ok(p);
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:label:info')")
    public R info(@PathVariable("id") Integer id) {
        Label entity = this.labelService.getEntity(id);
        return R.ok(entity);
    }

    @Log("保存标签数据")
    @Fill(FillType.INSERT)
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:label:save')")
    public R save(Label entity) {
        this.labelService.saveEntity(entity);
        return R.ok();
    }

    @Log("修改标签数据")
    @Fill(FillType.UPDATE)
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:label:update')")
    public R update(Label entity) {
        this.labelService.updateEntity(entity);
        return R.ok();
    }

    @Log("修改标签状态")
    @PostMapping("/change")
    @PreAuthorize(value = "hasAuthority('sys:label:update')")
    public R change(@RequestBody Label entity) {
        Label label = this.labelService.getEntity(entity.getId());
        if(label == null) {
            return R.error("修改星级不存在!");
        }
        if(entity.getStatus() == label.getStatus().intValue()) {
            return R.error(S.USER_STATUS_PARAMTER_ERROR);
        }
        this.labelService.updateEntity(entity);
        return R.ok();
    }

}
