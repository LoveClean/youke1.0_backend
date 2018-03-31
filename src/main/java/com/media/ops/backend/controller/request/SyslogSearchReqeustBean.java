package com.media.ops.backend.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class SyslogSearchReqeustBean {
	@ApiModelProperty(value = "操作员工号")
	private String account;
	@ApiModelProperty(value = "操作员邮箱")
	private String email;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
