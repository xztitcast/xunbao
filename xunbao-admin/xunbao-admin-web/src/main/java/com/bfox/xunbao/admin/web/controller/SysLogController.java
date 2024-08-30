package com.bfox.xunbao.admin.web.controller;

import com.bfox.xunbao.admin.web.entity.SysLog;
import com.bfox.xunbao.admin.web.modelAndView.model.UserModel;
import com.bfox.xunbao.admin.web.service.SysLogService;
import com.bfox.xunbao.admin.web.support.excel.SimpleExcelWriter;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 管理系统日志控制器
 * @author eden
 * @time 2022年7月22日 上午11:36:22
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {

	@Autowired
	private SysLogService sysLogService;
	
	@GetMapping("/list")
	@PreAuthorize(value = "hasAuthority('sys:log:list')")
	public R list(UserModel form) {
		P<SysLog> p = sysLogService.getSysLogList(form);
		return R.ok(p);
	}

	/**
	 * 导出
	 * @param form
	 * @return
	 */
	@PostMapping("export")
	public R export(@RequestBody UserModel form) {
		SimpleExcelWriter<UserModel, SysLog> writer = new SimpleExcelWriter<>(this.sysLogService, form, SysLog.class);
		writer.export("系统日志");
		return R.ok();
	}
}
