package com.bfox.xunbao.admin.web.controller.framework;


import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.i.service.DevelopService;
import com.bfox.xunbao.framework.view.DevelopView;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 后台管理 开发语言控制器
 * @Author Eden
 * @Date 2025/3/30 21:19
 */
@RestController
@RequestMapping("/sys/develop")
public class SysDevelopController {

    @DubboReference
    private DevelopService developService;

    /**
     * 选择器
     * @return
     */
    @GetMapping("/select")
    public R select() {
        List<DevelopView> dataList = developService.getDataList();
        return R.ok(dataList);
    }
}
