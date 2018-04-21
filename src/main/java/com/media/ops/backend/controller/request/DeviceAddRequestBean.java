package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class DeviceAddRequestBean {
	@NotBlank(message = "设备编码不能为空")
	@ApiModelProperty(value = "设备编码code，必填", required = true)
	private String code;
	@ApiModelProperty(value = "设备物理地址address")
	private String address;
	@ApiModelProperty(value = "设备类型type")
	private String type;
	@ApiModelProperty(value = "设备分组id")
	private Integer groupid;
	@ApiModelProperty(value = "设备品牌brand")
	private String brand;
	@ApiModelProperty(value = "设备规格spec")
	private String spec;
	@ApiModelProperty(value = "所属区域areaId")
	private String areaId;
	@ApiModelProperty(value = "所属楼宇buildingId")
	private Integer buildingId;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}


}
