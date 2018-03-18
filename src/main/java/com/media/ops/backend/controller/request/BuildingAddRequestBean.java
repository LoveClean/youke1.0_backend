package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class BuildingAddRequestBean {
	
	@NotBlank(message = "name不能为空")
	@ApiModelProperty(value = "name，必填", required = true)
    private String name;
	
	@NotBlank(message = "areaid不能为空")
	@ApiModelProperty(value = "areaid，区域必填", required = true)
    private String areaid;
	
	@ApiModelProperty(value = "address，具体地址")
    private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
