package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class InfoCheckRequestBean {
	@NotBlank(message = "类型不能为空")
	@ApiModelProperty(value = "值为account或email或phone，必填", required = true)
	private String field;
	@NotBlank(message = "值不能为空")
	@ApiModelProperty(value = "工号或邮箱，必填", required = true)
	private String value;
	@ApiModelProperty(value = "类型")
	private int type;

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
}
