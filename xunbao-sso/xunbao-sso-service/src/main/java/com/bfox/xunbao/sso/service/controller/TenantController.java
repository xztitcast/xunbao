package com.bfox.xunbao.sso.service.controller;

import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.sso.i.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 机构信息 前端控制器
 * </p>
 *
 * @author Eden
 * @since 2024-07-27 22:41:07
 */
@RestController
@RequestMapping("/sso/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @GetMapping("/info")
    public R info() {
        tenantService.getTenantIdList(1L);
        return R.ok();
    }
}
