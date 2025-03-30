package com.bfox.xunbao.framework.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.framework.entity.Develop;
import com.bfox.xunbao.framework.view.DevelopView;

import java.util.List;

/**
 * <p>
 * 开发语言表 服务类
 * </p>
 *
 * @author Eden
 * @since 2025-03-30 18:38:07
 */
public interface DevelopService extends FrameworkService<Develop, Integer> {

    /**
     * 获取数据列表
     * @return
     */
    List<DevelopView> getDataList();



}
