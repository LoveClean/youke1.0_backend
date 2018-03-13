package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AdAddRequestBean {
	@NotBlank(message = "广告名称不能为空")
	@ApiModelProperty(value = "广告名称name，必填", required = true)
	private String name;  //广告名称
	
	@NotBlank(message = "groupId不能为空")
	@ApiModelProperty(value = "广告分组groudId，必填", required = true)
    private Integer groupid;  //分组id
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	
}
