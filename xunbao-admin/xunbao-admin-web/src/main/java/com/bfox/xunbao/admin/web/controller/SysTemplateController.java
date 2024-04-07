package com.bfox.xunbao.admin.web.controller;

import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.common.core.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Eden
 * @Date 2024/4/7 14:45
 */
@RestController
@RequestMapping("/sys/template")
public class SysTemplateController {

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('sys:#:list')")
    public R list() {

        return R.ok();
    }

    /**
     * 获取单条数据信息
     * @return
     */
    @GetMapping("/info")
    @PreAuthorize(value = "hasAuthority('sys:#:info')")
    public R info() {

        return R.ok();
    }

    @Log("保存{}数据")
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:#:save')")
    public R save() {

        return R.ok();
    }

    @Log("修改{}数据")
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:#:update')")
    public R update() {

        return R.ok();
    }

    @Log("删除{}数据")
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('sys:#:delete')")
    public R delete(@RequestBody Long[] ids) {

        return R.ok();
    }

}
