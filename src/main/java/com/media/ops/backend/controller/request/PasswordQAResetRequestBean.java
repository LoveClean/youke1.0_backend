package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class PasswordQAResetRequestBean {
	/**
	 * 用户名
	 */
	@NotBlank(message = "账号不能为空")
	@ApiModelProperty(value = "用户名，必填", required = true)
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	@Length(min = 3, max = 16, message = "请输入3-16位密码")
	@ApiModelProperty(value = "密码，必填", required = true)
	private String passwordNew;
	
	@NotBlank(message = "Token不能为空")
	@ApiModelProperty(value = "Token，必填", required = true)
	private String forgetToken;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	public String getForgetToken() {
		return forgetToken;
	}

	public void setForgetToken(String forgetToken) {
		this.forgetToken = forgetToken;
	}
	
	
}
