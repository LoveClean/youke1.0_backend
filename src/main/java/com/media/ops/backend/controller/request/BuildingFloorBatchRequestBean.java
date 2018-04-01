package com.media.ops.backend.controller.request;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class BuildingFloorBatchRequestBean {
	@NotBlank(message = "name不能为空")
	@ApiModelProperty(value = "name，必填", required = true)
    private String name;
	
	@NotBlank(message = "areaid不能为空")
	@ApiModelProperty(value = "areaid，区域必填", required = true)
    private String areaid;
	
	@ApiModelProperty(value = "address，具体地址")
    private String address;
	
	@ApiModelProperty(value = "楼层列表")
	private List<BuildingFloorAddRequestBean> buildingFloorAddRequestBeans;

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

	public List<BuildingFloorAddRequestBean> getBuildingFloorAddRequestBeans() {
		return buildingFloorAddRequestBeans;
	}

	public void setBuildingFloorAddRequestBeans(List<BuildingFloorAddRequestBean> buildingFloorAddRequestBeans) {
		this.buildingFloorAddRequestBeans = buildingFloorAddRequestBeans;
	}
	
	
	
	
}
