package com.media.ops.backend.controller.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class FloorDeviceAddRequestBean {
	@NotNull(message = "楼层Id不能为空")
	@ApiModelProperty(value = "楼层Id，必填", required = true)
	private Integer floorid;
	@NotNull(message = "设备Id不能为空")
	@ApiModelProperty(value = "设备Id，必填", required = true)
    private Integer deviceid;

	@ApiModelProperty(value = "平面图的X坐标")
    private Float x;
	@ApiModelProperty(value = "平面图的Y坐标")
    private Float y;
	@ApiModelProperty(value = "备注")
    private String memo;
	public Integer getFloorid() {
		return floorid;
	}
	public void setFloorid(Integer floorid) {
		this.floorid = floorid;
	}
	public Integer getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}
	public Float getX() {
		return x;
	}
	public void setX(Float x) {
		this.x = x;
	}
	public Float getY() {
		return y;
	}
	public void setY(Float y) {
		this.y = y;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
