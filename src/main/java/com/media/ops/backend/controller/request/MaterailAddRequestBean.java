package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class MaterailAddRequestBean {
	@NotBlank(message = "name不能为空")
	@ApiModelProperty(value = "name，必填", required = true)
	private String name;
	
	@NotBlank(message = "type不能为空")
	@ApiModelProperty(value = "type，必填", required = true)
	private String type;
	
	@NotBlank(message = "素材分组不能为空")
	@ApiModelProperty(value = "groupid，必填", required = true)
    private Integer groupid;
	
	@NotBlank(message = "素材地址不能为空")
	@ApiModelProperty(value = "path，必填", required = true)
    private String path;
	
	@ApiModelProperty(value = "操作用户", required = false)
	private String createBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	
	
}
