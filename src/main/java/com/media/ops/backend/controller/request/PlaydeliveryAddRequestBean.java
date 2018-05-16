package com.media.ops.backend.controller.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class PlaydeliveryAddRequestBean {
	@NotNull(message = "直播id不能为空")
	@ApiModelProperty(value = "直播id，必填", required = true)
    private Integer playid;//直播id
    
	@NotNull(message = "投放类型不能为空")
	@ApiModelProperty(value = "投放类型deliverytype，必填-0按设备分组，1按楼宇", required = true)
    private Integer delivertype; //投放类型

	@ApiModelProperty(value = "areaid区域ID")
    private String areaid;//区域ID，选填
	
	@NotNull(message = "设备分组ID或楼宇ID不能为空")
	@ApiModelProperty(value = "设备分组/楼宇ID，必填", required = true)
    //private Integer groupid; //楼宇ID或设备分组ID
	private List<Integer> groupid;


	public Integer getPlayid() {
		return playid;
	}

	public void setPlayid(Integer playid) {
		this.playid = playid;
	}

	public Integer getDelivertype() {
		return delivertype;
	}

	public void setDelivertype(Integer delivertype) {
		this.delivertype = delivertype;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public List<Integer> getGroupid() {
		return groupid;
	}

	public void setGroupid(List<Integer> groupid) {
		this.groupid = groupid;
	}
   
    
}
