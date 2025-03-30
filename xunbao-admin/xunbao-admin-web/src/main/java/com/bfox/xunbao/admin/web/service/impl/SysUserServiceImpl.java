package com.bfox.xunbao.admin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.mapper.SysUserMapper;
import com.bfox.xunbao.admin.web.modelAndView.model.UserModel;
import com.bfox.xunbao.admin.web.service.SysRoleService;
import com.bfox.xunbao.admin.web.service.SysUserRoleService;
import com.bfox.xunbao.admin.web.service.SysUserService;
import com.bfox.xunbao.common.core.Constant;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.S;
import com.bfox.xunbao.common.core.exception.custom.SysServiceException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统用户业务接口实现类
 * @author eden
 * @date 2018年7月23日 上午9:20:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Override
	public P<SysUser> getSysUserList(UserModel um, Long userId) {
		IPage<SysUser> page = new Page<>(um.getPageNum(), um.getPageSize());
		QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
		wrapper.eq(StringUtils.isNotBlank(um.getUsername()), "username", um.getUsername())
			   .eq(Constant.Sys.SUPER_ADMIN != userId ,"creator", userId).orderBy(true, um.getOrderByAsc(), um.getOrderField());
		page(page,wrapper);
		return new P<>(page.getTotal(), page.getRecords());
	}

	@Override
	public List<Long> getNodeUserIdList(Long userId) {
		List<SysUser> nodeUserList = this.listByMap(Map.of("creator", userId));
		return nodeUserList.stream().map(SysUser::getId).collect(Collectors.toList());
	}

	@Override
	public SysUser getByUserName(String username) {
		LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUsername, username);
		return getOne(wrapper);
	}
	
	@Override
	@Transactional
	public boolean saveOrUpdate(SysUser entity) {
		checkRole(entity);
		boolean saveOrUpdate = super.saveOrUpdate(entity);
		if(saveOrUpdate) {
			sysUserRoleService.saveOrUpdate(entity.getId(), entity.getRoleIdList());
		}
		return saveOrUpdate;
	}
	
	@Override
	public List<SysUser> getSysUserList(Long userId) {
		LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery(SysUser.class).ne(SysUser::getId, Constant.Sys.SUPER_ADMIN).eq(Constant.Sys.SUPER_ADMIN != userId, SysUser::getCreator, userId);
		return this.list(wrapper);
	}
	
	@Override
	public List<Long> getUserIdList(Long tisid) {
		List<SysUser> list = this.listByMap(Map.of("tisid", tisid));
		return list.stream().map(SysUser::getId).collect(Collectors.toList());
	}

	/**
	 * 校验角色
	 * @param user
	 */
	private void checkRole(SysUser user) {
		if (CollectionUtils.isEmpty(user.getRoleIdList())) {
			return ;
		}
		
		//if you not a super admin, you need to check if the user's role is self-created
		if(user.getCreator() == Constant.Sys.SUPER_ADMIN) {
			return;
		}
		//get the list of roles created by the user
		List<Long> roleIdList = sysRoleService.getRoleIdList(user.getCreator());
		
		//check permission
		if(!roleIdList.containsAll(user.getRoleIdList())) {
			throw new SysServiceException(S.USER_ADD_PERMISSIONS_ERROR);
		}
		
	}

}
