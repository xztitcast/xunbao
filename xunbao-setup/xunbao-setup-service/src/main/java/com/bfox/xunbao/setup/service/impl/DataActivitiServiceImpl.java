package com.bfox.xunbao.setup.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfox.xunbao.setup.entity.DataActiviti;
import com.bfox.xunbao.setup.mapper.DataActivitiMapper;
import com.bfox.xunbao.setup.i.service.DataActivitiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据审批流表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2024-09-08 18:49:54
 */
@Service
public class DataActivitiServiceImpl extends ServiceImpl<DataActivitiMapper, DataActiviti> implements IService<DataActiviti>, DataActivitiService {

}
