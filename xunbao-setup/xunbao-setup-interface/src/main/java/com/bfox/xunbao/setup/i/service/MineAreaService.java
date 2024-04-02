package com.bfox.xunbao.setup.i.service;

import java.util.List;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.setup.entity.MineArea;
import com.bfox.xunbao.setup.view.MineAreaView;

/**
 * "我的"页面区域Service业务接口
 * @author eden
 * @date 2023年3月2日 下午8:17:07
 */
public interface MineAreaService extends FrameworkService<MineArea, Long> {

	/**
	 * 获取区域数据列表
	 * @param mineId 我的页面主键ID
	 * @return
	 */
	List<MineAreaView> getMineAreaList(Integer mineId);
}
