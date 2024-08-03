package com.bfox.xunbao.admin.web.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 系统登录事件源
 * @author eden
 * @date 2022年11月16日 下午9:50:20
 */
@Getter
public class SysLoginEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private String ip;

	public SysLoginEvent(Object source) {
		super(source);
	}

	public SysLoginEvent(Object source, String ip) {
		super(source);
		this.ip = ip;
	}

}
