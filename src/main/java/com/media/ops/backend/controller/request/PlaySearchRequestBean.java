package com.media.ops.backend.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class PlaySearchRequestBean {
	@ApiModelProperty(value = "直播员ID")
    private Integer playerId;
	
	@ApiModelProperty(value = "状态(0,未开放；1,预告中；2,直播中；3,直播结束)")
    private Integer status;
	
	@ApiModelProperty(value = "直播标题")
	private String  playTitle;
	
	@ApiModelProperty(value = "开始时间")
	private String beginTime;
	
	@ApiModelProperty(value = "结束时间")
	private String endTime;
	
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPlayTitle() {
		return playTitle;
	}
	public void setPlayTitle(String playTitle) {
		this.playTitle = playTitle;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
