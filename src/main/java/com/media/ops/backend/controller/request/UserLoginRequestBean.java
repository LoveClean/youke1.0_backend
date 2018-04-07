package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Title: UserLoginRequestBean.java
 * @Package com.zhishangquan.server.controller.request
 * @Description: 登录RequestBean
 * @version V1.0
 */
@ApiModel
public class UserLoginRequestBean {
	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空")
	@ApiModelProperty(value = "用户名，必填", required = true)
	private String loginName;

	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	@Length(min = 3, max = 16, message = "请输入3-16位密码")
	@ApiModelProperty(value = "密码，必填", required = true)
	private String loginPwd;
	
	@NotBlank(message = "验证码不能为空")
	@ApiModelProperty(value = "验证码，必填", required = true)
	private String verifyCode;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	

}
