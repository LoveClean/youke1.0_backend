package com.media.ops.backend.controller.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class DeviceSearchRequestBean implements Serializable {

	private static final long serialVersionUID = -5772418760357199184L;
	private static final int DEFAULT_PAGE = 1;
	private static final int DEFAULT_SIZE = 999;

	@ApiModelProperty(value = "设备编号")
	private String code;
	@ApiModelProperty(value = "城市ID")
	private String cityId;
	@ApiModelProperty(value = "区域ID")
	private String areaId;
	@ApiModelProperty(value = "地址关键词")
	private String address;
	@ApiModelProperty(value = "楼宇id")
	private Integer buildingId;   //楼宇id
	@ApiModelProperty(value = "设备分组id")
	private Integer groupId;   //设备分组id

	/**
	 * 当前页，默认1
	 */
	@Min(value = 1, message = "pageNum cannot be less then 1")
	@ApiModelProperty(value = "当前页，首页为1")
	private int pageNum = DEFAULT_PAGE;

	/**
	 * 每页多少条，默认10条 0查全部
	 */
	@Min(value = 0, message = "pageSize cannot be less then 0")
	@Max(value = 1000, message = "pageSize cannot be more then 20")
	@ApiModelProperty(value = "每页显示条数，须大于0，默认10条")
	private int pageSize = DEFAULT_SIZE;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum <= 0 ? 1 : pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = (pageSize <= 0 || pageSize >= 1000) ? 999 : pageSize;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	

}
