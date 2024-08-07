package com.bfox.xunbao.admin.web.modelAndView.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginModel extends SysModel {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "uuid不能为空!")
	private String uuid;

	public LoginModel() {}

	public LoginModel(String username) {
		super.username = username;
	}
}
