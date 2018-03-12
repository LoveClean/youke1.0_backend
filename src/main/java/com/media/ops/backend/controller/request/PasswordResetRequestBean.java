package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class PasswordResetRequestBean {
	@NotBlank(message = "密码不能为空")
	@Length(min = 3, max = 16, message = "请输入3-16位密码")
	@ApiModelProperty(value = "旧密码，必填", required = true)
	private  String passwordOld;
	
	@NotBlank(message = "密码不能为空")
	@Length(min = 3, max = 16, message = "请输入3-16位密码")
	@ApiModelProperty(value = "新密码，必填", required = true)
	private String passwordNew;

	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}
	
	

}
