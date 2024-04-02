package com.bfox.xunbao.setup.i.service;

import java.util.List;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.setup.view.ModuleView;
import com.bfox.xunbao.setup.entity.Module;

/**
 * 模块配置Service业务接口
 * @author eden
 * @date 2023年2月14日 下午6:41:19
 */
public interface ModuleService extends FrameworkService<Module, Long> {


    /**
	 * 获取机构模块列表数据
 	 * @param tisid 机构ID
	 * @return
	 */
	List<ModuleView> getModuleList(Long tisid);
}
