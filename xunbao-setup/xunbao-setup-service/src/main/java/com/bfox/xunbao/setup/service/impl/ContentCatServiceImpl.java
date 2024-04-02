package com.bfox.xunbao.setup.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfox.xunbao.setup.entity.ContentCat;
import com.bfox.xunbao.setup.mapper.ContentCatMapper;
import com.bfox.xunbao.setup.i.service.ContentCatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 内容分类表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2024-04-02 23:41:38
 */
@Service
@DubboService(interfaceClass = ContentCatService.class)
public class ContentCatServiceImpl extends ServiceImpl<ContentCatMapper, ContentCat> implements IService<ContentCat>, ContentCatService {

}
