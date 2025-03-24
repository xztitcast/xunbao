package com.bfox.xunbao.common.core.injecter;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 缓存实体对象
 */
@Getter
@Setter
public class Principal implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 用户头像
	 */
	private String avatar;

	/**
	 * 微信union_id
	 */
	private String unionId;

	/**
	 * 小程序openid
	 */
	private String openid;

	/**
	 * 用户唯一标识
	 */
	private String uuid;

	/**
	 * 类型1:真实用户 2:虚拟用户
	 */
	private Short type;

	/**
	 * IP地址
	 */
	private String ip;

	/**
	 * 创建时间
	 */
	private Date created;

	/**
	 * 更新时间
	 */
	private Date updated;

}
