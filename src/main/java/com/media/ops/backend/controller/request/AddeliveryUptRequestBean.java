package com.media.ops.backend.controller.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class AddeliveryUptRequestBean {
	@NotNull(message = "投放记录id不能为空")
	@ApiModelProperty(value = "id，必填", required = true)
	private Integer id; //id
	
    //private Integer adid;//广告id
	@NotNull(message = "广告类型不能为空")
	@ApiModelProperty(value = "广告类型adtype，必填-0为正常，1为插播", required = true)
    private Integer adtype; //广告类型，0为普通广告，1为插播
	
	@NotNull(message = "投放类型不能为空")
	@ApiModelProperty(value = "投放类型deliverytype，必填-0按设备分组，1按楼宇", required = true)
    private Integer delivertype; //投放类型

	@ApiModelProperty(value = "areaid区域ID")
    private String areaid;//区域ID，选填
	
	@NotNull(message = "设备分组ID或楼宇ID不能为空")
	@ApiModelProperty(value = "设备分组/楼宇ID，必填", required = true)
    private Integer groupid; //楼宇ID或设备分组ID

	@NotNull(message = "开始播放时间不能空")
	@ApiModelProperty(value = "开始投放时间，必填", required = true)
    private Date begintime;  //开始时间

	@NotNull(message = "结束投放时间不能空")
	@ApiModelProperty(value = "结束投放时间，必填", required = true)
    private Date endtime;  //结束时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Integer getAdid() {
//		return adid;
//	}
//
//	public void setAdid(Integer adid) {
//		this.adid = adid;
//	}

	public Integer getAdtype() {
		return adtype;
	}

	public void setAdtype(Integer adtype) {
		this.adtype = adtype;
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

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
    
    
}
