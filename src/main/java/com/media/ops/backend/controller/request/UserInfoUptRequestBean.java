package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.Email;

import io.swagger.annotations.ApiModelProperty;

public class UserInfoUptRequestBean {
	@ApiModelProperty(value = "id,修改他人信息的必填，修改自己信息不填")
	private Integer id;
	
    @ApiModelProperty(value = "姓名，选填")
    private String trueName;
    
    @Email(message="邮箱格式不正确")
    @ApiModelProperty(value = "邮箱，必填", required = true)
    private String email;
    
    @ApiModelProperty(value = "手机号码，必填", required = true)
    private String phone;

    @ApiModelProperty(value = "账号类型，管理员0或直播员1，选填")
	private Integer type;
    @ApiModelProperty(value = "账号状态，正常1或禁用2，选填")
	private byte status;
    
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
    
    
}
