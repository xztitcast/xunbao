package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.Label;
import com.bfox.xunbao.framework.i.service.LabelService;
import com.bfox.xunbao.framework.mapper.LabelMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-04 01:28:33
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements IService<Label>, LabelService {

}
