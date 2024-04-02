package com.bfox.xunbao.setup.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.setup.entity.LayoutNavigatePath;
import com.bfox.xunbao.setup.i.service.LayoutNavigatePathService;
import com.bfox.xunbao.setup.mapper.LayoutNavigatePathMapper;

/**
 * 导航路径Service业务逻辑实现类
 * @author eden
 * @date 2023年2月28日 下午4:51:46
 */
@Service("navigatePathService")
@DubboService(interfaceClass = LayoutNavigatePathService.class)
public class LayoutNavigatePathServiceImpl extends ServiceImpl<LayoutNavigatePathMapper, LayoutNavigatePath> implements IService<LayoutNavigatePath>, LayoutNavigatePathService {

	@Override
	public List<LayoutNavigatePath> getNavigatePathList() {
		return this.list();
	}

}
