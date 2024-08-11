package com.bfox.xunbao.admin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.admin.web.entity.SysLog;
import com.bfox.xunbao.admin.web.event.SysLoginEvent;
import com.bfox.xunbao.admin.web.mapper.SysLogMapper;
import com.bfox.xunbao.admin.web.modelAndView.model.LoginModel;
import com.bfox.xunbao.admin.web.modelAndView.model.UserModel;
import com.bfox.xunbao.admin.web.service.SysLogService;
import com.bfox.xunbao.common.core.P;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService, ApplicationListener<SysLoginEvent> {

	@Override
	public P<SysLog> getSysLogList(UserModel um) {
		IPage<SysLog> page = new Page<>(um.getPageNum(), um.getPageSize());
		QueryWrapper<SysLog> query = new QueryWrapper<>();
		query.eq(StringUtils.isNotBlank(um.getUsername()), "`username`", um.getUsername()).orderBy(true, um.getOrder(), um.getOrderField());
		page(page, query);
		return new P<>(page.getTotal(), page.getRecords());
	}

	@Async
	@Override
	public void onApplicationEvent(SysLoginEvent event) {
		SysLog sysLog = new SysLog();
		Object source = event.getSource();
		if(source instanceof LoginModel) {
			LoginModel form = (LoginModel)source;
			sysLog.setOperation("用户登录");
			sysLog.setUsername(form.getUsername());
			sysLog.setMethod("com.jc.smart.admin.web.controller.SysLoginController.login()");
		}else {
			sysLog.setOperation("退出登录");
			sysLog.setUsername(event.getSource().toString());
			sysLog.setMethod("com.jc.smart.admin.web.controller.SysLoginController.logout()");
		}
		sysLog.setTime(0L);
		sysLog.setIp(event.getIp());
		sysLog.setCreated(new Date());
		this.save(sysLog);
	}

	@Override
	public P<SysLog> getExcelList(UserModel model) {
		return this.getSysLogList(model);
	}

}
