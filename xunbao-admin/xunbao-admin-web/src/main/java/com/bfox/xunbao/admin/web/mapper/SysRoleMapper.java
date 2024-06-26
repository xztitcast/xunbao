package com.bfox.xunbao.admin.web.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bfox.xunbao.admin.web.entity.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * 查询用户的所有菜单ID
	 * @param userId
	 * @return
	 */
	List<Long> selectAllMenuId(Long userId);
}