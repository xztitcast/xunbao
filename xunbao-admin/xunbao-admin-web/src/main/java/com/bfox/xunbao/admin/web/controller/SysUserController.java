package com.bfox.xunbao.admin.web.controller;

import com.bfox.xunbao.admin.web.annotation.Log;
import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.modelAndView.model.PasswordModel;
import com.bfox.xunbao.admin.web.modelAndView.model.UserModel;
import com.bfox.xunbao.admin.web.service.SysUserRoleService;
import com.bfox.xunbao.admin.web.service.SysUserService;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.S;
import jakarta.validation.Valid;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.bfox.xunbao.common.core.Constant.Sys.SUPER_ADMIN;

/**
 * 管理系统用户控制器
 * @author eden
 * @time 2022年7月22日 下午12:16:09
 */
@Validated
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * 获取当前登陆用户创建的所有用户列表
	 * @param form
	 * @return
	 */
	@GetMapping("/list")
	@PreAuthorize(value = "hasAuthority('sys:user:list')")
	public R list(UserModel form) {
		P<SysUser> list = sysUserService.getSysUserList(form, getUserId());
		return R.ok(list);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		return R.ok(getUser());
	}
	
	/**
	 * 获取单个用户信息
	 * @param userId
	 * @return
	 */
	@GetMapping("/info/{userId}")
	@PreAuthorize(value = "hasAuthority('sys:user:info')")
	public R info(@PathVariable("userId") Long userId) {
		SysUser user = sysUserService.getById(userId);
		if(user == null) {
			return R.error(S.USER_NOTFOUND_ERROR);
		}
		List<Long> roleIdList = sysUserRoleService.getRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		return R.ok(user);
	}
	
	@Log("保存用户数据")
	@PostMapping("/save")
	@PreAuthorize(value = "hasAuthority('sys:user:save')")
	public R save(@RequestBody SysUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setSalt(RandomStringUtils.randomAlphanumeric(20));
		user.setCreator(getUserId());
		sysUserService.saveOrUpdate(user);
		return R.ok();
	}
	
	@Log("修改用户数据")
	@PostMapping("/update")
	@PreAuthorize(value = "hasAuthority('sys:user:update')")
	public R update(@RequestBody SysUser user) throws Exception{
		user.setCreator(getUserId());
		sysUserService.saveOrUpdate(user);
		return R.ok();
	}

	@Log("修改用户密码")
	@PutMapping ("/password")
	@PreAuthorize(value = "hasAuthority('sys:user:update')")
	public R password(@Valid @RequestBody PasswordModel pm){
		SysUser sysUser = sysUserService.getById(getUserId());
		if(sysUser == null) {
			return R.error(S.USER_NOTFOUND_ERROR);
		}
		String password = passwordEncoder.encode(pm.getPassword());
		if(!passwordEncoder.matches(password, sysUser.getPassword())) {
			return R.error(S.USER_ORIGINAL_PWD_ERROR);
		}
		sysUser.setPassword(passwordEncoder.encode(pm.getNewPassword()));
		sysUser.setSalt(RandomStringUtils.randomAlphanumeric(20));
		sysUserService.updateById(sysUser);
		return R.ok();
	}

	@Log("修改用户账户状态")
	@PostMapping("/change")
	@PreAuthorize(value = "hasAuthority('sys:user:update')")
	public R change(@RequestBody SysUser em){
		SysUser sysUser = sysUserService.getById(em.getId());
		if(sysUser == null) {
			return R.error(S.USER_NOTFOUND_ERROR);
		}
		if (getUserId() != SUPER_ADMIN) {
			List<Long> nodeUserIdList = sysUserService.getNodeUserIdList(getUserId());
			if (!nodeUserIdList.contains(em.getId())){
				return R.error(S.USER_NOT_PERMISSION_ERROR);
			}
		}
		if(sysUser.getStatus() == em.getStatus().intValue()) {
			return R.error(S.USER_STATUS_PARAMETER_ERROR);
		}
		sysUserService.updateById(em);
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@Log("删除用户数据")
	@DeleteMapping("/delete")
	@PreAuthorize(value = "hasAuthority('sys:user:delete')")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, SUPER_ADMIN)){
			return R.error(S.USER_REMOVE_SUPER_ADMIN_ERROR);
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error(S.USER_CURRENT_REMOVE_ERROR);
		}
		
		sysUserService.removeByIds(Arrays.asList(userIds));
		
		return R.ok();
	}
	
}
