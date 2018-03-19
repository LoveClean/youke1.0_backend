package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class InfoCheckRequestBean {
	@NotBlank(message = "类型不能为空")
	@ApiModelProperty(value = "值为account或email，必填", required = true)
	private String type;
	@NotBlank(message = "值不能为空")
	@ApiModelProperty(value = "工号或邮箱，必填", required = true)
	private String value;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
