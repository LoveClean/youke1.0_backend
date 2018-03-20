package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class MaterialAddRequestBean {
	@NotBlank(message = "name不能为空")
	@ApiModelProperty(value = "素材name，必填", required = true)
	private String name;
	
	@NotBlank(message = "type不能为空")
	@ApiModelProperty(value = "素材type，必填,图片、音频、视频3种值", required = true)
	private String type;
	
	@NotBlank(message = "素材分组不能为空")
	@ApiModelProperty(value = "groupid，素材分组，必填", required = true)
    private Integer groupid;
	
	@NotBlank(message = "素材地址不能为空")
	@ApiModelProperty(value = "path，存储路径，必填", required = true)
    private String path;
	
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

	
	
	
}
