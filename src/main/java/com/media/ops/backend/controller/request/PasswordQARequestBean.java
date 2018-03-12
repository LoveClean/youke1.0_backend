package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class PasswordQARequestBean {

	@NotBlank(message = "账号不能为空")
	@ApiModelProperty(value = "账号，必填", required = true)
	private String username;
	
	@NotBlank(message = "密保问题不能为空")
	@ApiModelProperty(value = "密保问题，必填", required = true)
	private String question;
	
	@NotBlank(message = "密保答案不能为空")
	@ApiModelProperty(value = "密保答案，必填", required = true)
	private String answer;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
