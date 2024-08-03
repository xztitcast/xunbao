package com.bfox.xunbao.framework.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.framework.entity.Region;

import java.util.List;

/**
 * <p>
 * 全国行政省市区信息表 服务类
 * </p>
 *
 * @author Eden
 * @since 2024-08-01 22:48:47
 */
public interface RegionService extends FrameworkService<Region, Long> {

    /**
     * 获取区域列表
     * @param level 级别
     * @return
     */
    List<Region> getRegionList(Integer level);
}
