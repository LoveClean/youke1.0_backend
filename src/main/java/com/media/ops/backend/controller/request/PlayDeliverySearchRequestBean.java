package com.media.ops.backend.controller.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class PlayDeliverySearchRequestBean implements Serializable {

	private static final long serialVersionUID = -5772418760357199184L;
	private static final int DEFAULT_PAGE = 1;
	private static final int DEFAULT_SIZE = 999;


	@ApiModelProperty(value = "城市ID")
	private String cityId;
	@ApiModelProperty(value = "区域ID")
	private String areaId;

	@ApiModelProperty(value = "投放类型")
	private Integer deliveryType;   //投放类型 0为设备，1为楼宇
	@ApiModelProperty(value = "楼宇/设备分组id")
	private Integer groupId;   //分组id

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



	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	

}
