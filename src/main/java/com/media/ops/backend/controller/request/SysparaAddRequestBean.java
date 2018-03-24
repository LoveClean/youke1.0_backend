package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class SysparaAddRequestBean {
	@NotBlank(message = "参数名称不能为空")
	@ApiModelProperty(value = "参数名称，必填", required = true)
	private String name;
	@ApiModelProperty(value = "参数值，必填")
	private String value;
	@ApiModelProperty(value = "备注")
	private String note;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
