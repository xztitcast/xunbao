package com.bfox.xunbao.admin.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfox.xunbao.admin.web.entity.SysRole;
import com.bfox.xunbao.admin.web.modelAndView.model.RoleModel;
import com.bfox.xunbao.common.core.P;

import java.util.List;

/**
 * 系统角色业务接口
 * @author eden
 * @date 2018年10月23日 上午9:32:43
 */
public interface SysRoleService extends IService<SysRole> {

	void deleteBatch(Long... roleIds);
	
	/**
	 * 查询用户创建的角色ID列表
	 * @param creator 创建者ID
	 * @return
	 */
	List<Long> getRoleIdList(Long creator);
	
	/**
	 * 获取角色列表
	 * @param form
	 * @param creator
	 * @return
	 */
	P<SysRole> getSysRoleList(RoleModel form, long creator);

}
