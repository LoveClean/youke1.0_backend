package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.Email;

import io.swagger.annotations.ApiModelProperty;

public class UserUptRequestBean {

	@ApiModelProperty(value = "id")
	private Integer id;
	
    @ApiModelProperty(value = "姓名，选填")
    private String trueName;
    
    @Email(message="邮箱格式不正确")
    @ApiModelProperty(value = "邮箱，必填", required = true)
    private String email;
    
    @ApiModelProperty(value = "手机号码，必填", required = true)
    private String phone;

    @ApiModelProperty(value = "旧密码，为空表示不修改")
    private String oldPwd;
    @ApiModelProperty(value = "新密码，选填")
    private String newPwd;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

    
    
}
