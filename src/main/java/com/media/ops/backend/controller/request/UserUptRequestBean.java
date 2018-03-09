package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.Email;

import io.swagger.annotations.ApiModelProperty;

public class UserUptRequestBean {

    @ApiModelProperty(value = "姓名，选填")
    private String trueName;
    
    @Email(message="邮箱格式不正确")
    @ApiModelProperty(value = "邮箱，必填", required = true)
    private String email;
    
    @ApiModelProperty(value = "手机号码，必填", required = true)
    private String phone;

    @ApiModelProperty(value = "找回密码问题，选填")
    private String question;
    @ApiModelProperty(value = "找回密码答案，选填")
    private String answer;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
    
    
}
