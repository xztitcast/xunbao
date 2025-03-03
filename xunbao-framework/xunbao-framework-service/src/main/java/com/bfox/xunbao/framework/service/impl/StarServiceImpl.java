package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.Star;
import com.bfox.xunbao.framework.i.service.StarService;
import com.bfox.xunbao.framework.mapper.StarMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 星级表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-04 01:28:33
 */
@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements IService<Star>, StarService {

}
