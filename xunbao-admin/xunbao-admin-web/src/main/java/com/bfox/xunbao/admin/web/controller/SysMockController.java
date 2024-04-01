package com.bfox.xunbao.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.modelAndView.model.UserModel;
import com.bfox.xunbao.admin.web.service.SysUserService;
import com.bfox.xunbao.common.core.BaseModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.utils.AESUtil;

@RestController
@RequestMapping("/test/mock")
public class SysMockController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/create")
    public R create(UserModel form){
        P<SysUser> list = sysUserService.getSysUserList(form, 1L);
        return R.ok(list);
    }

    @GetMapping("/demo1")
    public R demo1(BaseModel vo){
        return R.ok(vo);
    }
    
    @GetMapping("/demo2")
    public R demo2() {
    	String encrypt = AESUtil.encrypt("123456");
    	return R.ok(encrypt);
    }
}
