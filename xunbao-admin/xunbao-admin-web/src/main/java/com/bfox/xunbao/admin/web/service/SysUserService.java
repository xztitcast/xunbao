package com.bfox.xunbao.admin.web.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.modelAndView.model.UserModel;
import com.bfox.xunbao.common.core.P;

/**
 * 系统用户业务接口
 * @author eden
 * @date 2018年7月23日 上午9:21:12
 */
public interface SysUserService extends IService<SysUser> {
	
	/**
	 * 查询用户列表
	 * @param form 条件对象
	 * @param userId
	 * @return
	 */
	P<SysUser> getSysUserList(UserModel form, Long userId);


	/**
	 * 获取子级用户ID列表
	 * @param userId
	 * @return
	 */
	List<Long> getNodeUserIdList(Long userId);

	/**
	 * 根据用户名，查询系统用户
	 * @param username 用户名
	 * @return
	 */
	SysUser getByUserName(String username);
	
	/**
	 * 查询用户列表
	 * @param userId
	 * @return
	 */
	List<SysUser> getSysUserList(Long userId);
	
	/**
	 * 获取用户ID列表
	 * @param tisid
	 * @return
	 */
	List<Long> getUserIdList(Long tisid);
	
}
