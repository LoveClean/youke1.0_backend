package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class MaterialGroupAddRequestBean {

	private static final int DEFAULT_Id = 0;
	
	@NotBlank(message = "groupName不能为空")
	@ApiModelProperty(value = "groupName，必填", required = true)
	private String groupName;

	@ApiModelProperty(value = "parentId")
	private Integer parentId= DEFAULT_Id;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId<0? DEFAULT_Id: parentId;
	}
}
