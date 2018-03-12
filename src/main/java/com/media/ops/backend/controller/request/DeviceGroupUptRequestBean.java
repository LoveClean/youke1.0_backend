package com.media.ops.backend.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class DeviceGroupUptRequestBean {
	@NotNull(message = "groupid不能为空")
	@ApiModelProperty(value = "groupid，必填", required = true)
	private Integer groupId;
	
	@NotBlank(message = "groupName不能为空")
	@ApiModelProperty(value = "groupName，必填", required = true)
	private String groupName;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
