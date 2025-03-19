package com.bfox.xunbao.framework.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.framework.entity.Item;

import java.util.List;

/**
 * <p>
 * 奖励表 服务类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
public interface ItemService extends FrameworkService<Item, Long> {

    /**
     * 只查询100个奖品
     * 按照时间倒序
     * 防止奖品随着时间的拉长数据变大全部查出
     * @return
     */
    List<Item> getSelection();
}
