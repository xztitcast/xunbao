package com.bfox.xunbao.framework.service.controller;

import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.i.service.DevelopService;
import com.bfox.xunbao.framework.view.DevelopView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 开发语言表 前端控制器
 * </p>
 *
 * @author Eden
 * @since 2025-03-30 18:38:07
 */
@RestController
@RequestMapping("/framework/develop")
public class DevelopController {

    @Autowired
    private DevelopService developService;

    /**
     * 列表数据
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<DevelopView> dataList = this.developService.getDataList();
        return R.ok(dataList);
    }

}
