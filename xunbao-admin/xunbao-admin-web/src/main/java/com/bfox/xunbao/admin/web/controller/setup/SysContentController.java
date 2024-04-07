package com.bfox.xunbao.admin.web.controller.setup;

import com.bfox.xunbao.admin.web.controller.BaseController;
import com.bfox.xunbao.common.core.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/content")
public class SysContentController extends BaseController {

    @GetMapping("/list")
    public R list(@RequestParam Long parentId) {

        return R.ok();
    }
}
