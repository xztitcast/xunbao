package com.bfox.xunbao.framework.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.framework.entity.Star;

import java.util.List;

/**
 * <p>
 * 星级表 服务类
 * </p>
 *
 * @author Eden
 * @since 2025-03-04 01:28:33
 */
public interface StarService extends FrameworkService<Star, Long> {

    /**
     * 获取选择器
     * @return
     */
    List<Star> getSelection();
}
