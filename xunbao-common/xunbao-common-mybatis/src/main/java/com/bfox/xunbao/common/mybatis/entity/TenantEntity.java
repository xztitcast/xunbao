package com.bfox.xunbao.common.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 多租户使用
 * @author Eden
 * @date 2024年3月30日 下午5:53:22
 */
public abstract class TenantEntity extends BaseEntity {
	
	/**
	 * 机构ID
	 */
	protected Long tenantId;

	/**
	 * 机构名称
	 */
	protected String tenantName;

	@JsonIgnore
	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	@JsonIgnore
	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
}
