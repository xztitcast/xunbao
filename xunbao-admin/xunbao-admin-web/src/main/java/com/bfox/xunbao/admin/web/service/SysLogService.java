package com.bfox.xunbao.admin.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfox.xunbao.admin.web.entity.SysLog;
import com.bfox.xunbao.admin.web.modelAndView.model.UserModel;
import com.bfox.xunbao.common.core.P;

/**
 * 系统日志业务接口
 * @author eden
 * @date 2018年7月23日 上午9:31:35
 */
public interface SysLogService extends IService<SysLog> {

	/**
	 * 获取日志代表
	 * @param from
	 * @return
	 */
	P<SysLog> getSysLogList(UserModel from);
	
}
