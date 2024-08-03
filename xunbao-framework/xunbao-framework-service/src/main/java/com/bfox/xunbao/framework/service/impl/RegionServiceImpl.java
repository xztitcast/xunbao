package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.enums.BaseEnum;
import com.bfox.xunbao.framework.entity.Region;
import com.bfox.xunbao.framework.i.service.RegionService;
import com.bfox.xunbao.framework.mapper.RegionMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 全国行政省市区信息表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2024-08-01 22:48:47
 */
@Service
@DubboService(interfaceClass = RegionService.class)
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IService<Region>, RegionService {

    @Override
    public List<Region> getRegionList(Integer level) {
        LambdaQueryWrapper<Region> queryWrapper = Wrappers.lambdaQuery(Region.class).eq(Region::getStatus, BaseEnum.ONE).le(level != null, Region::getLevel, level);
        return this.list(queryWrapper);
    }
}
