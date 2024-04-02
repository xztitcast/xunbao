package com.bfox.xunbao.setup.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfox.xunbao.setup.entity.Content;
import com.bfox.xunbao.setup.mapper.ContentMapper;
import com.bfox.xunbao.setup.i.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 内容表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2024-04-02 23:41:38
 */
@Service
@DubboService(interfaceClass = ContentService.class)
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IService<Content>, ContentService {

}
