package com.bfox.xunbao.framework.service.controller;

import com.alibaba.fastjson2.JSON;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.entity.model.DemoModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eden
 * @date 2024/8/23 16:02:02
 */
@RestController
@RequestMapping("/framework/demo")
public class DemoController {

    @PostMapping("/test")
    public R test(@RequestBody DemoModel model) {

        System.out.println(JSON.toJSONString(model));
        return R.ok();
    }
}
