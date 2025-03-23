package com.bfox.xunbao.admin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.admin.web.entity.SysUserRole;
import com.bfox.xunbao.admin.web.mapper.SysUserRoleMapper;
import com.bfox.xunbao.admin.web.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统用户与角色业务接口实现类
 * @author eden
 * @date 22018年7月23日 上午9:29:10
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
	
	@Override
	@Transactional
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if(CollectionUtils.isEmpty(roleIdList)) return;

		this.remove(new QueryWrapper<SysUserRole>().eq("user_id", userId));
		
		roleIdList.forEach(id -> {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setRoleId(id);
			sysUserRole.setUserId(userId);
			this.save(sysUserRole);
		});
	}
	
	@Override
	public List<Long> getRoleIdList(Long userId) {
		List<SysUserRole> list = this.listByMap(Map.of("user_id", userId));
		return list.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
	}

	@Override
	public int deleteBatch(Long[] roleIds) {
		LambdaQueryWrapper<SysUserRole> wrapper = Wrappers.lambdaQuery(SysUserRole.class).in(SysUserRole::getRoleId, Arrays.asList(roleIds));
		return this.baseMapper.delete(wrapper);
	}

}
