package com.media.ops.backend.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class BuildingUptRequestBean {
	@NotNull(message = "楼宇Id不能为空")
	@ApiModelProperty(value = "楼宇Id，必填", required = true)
	private Integer id;

	@NotBlank(message = "name不能为空")
	@ApiModelProperty(value = "name，必填", required = true)
    private String name;
	
	@NotBlank(message = "areaid不能为空")
	@ApiModelProperty(value = "areaid，区域必填", required = true)
    private String areaid;
	
	@ApiModelProperty(value = "address，具体地址")
    private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
