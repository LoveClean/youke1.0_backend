package com.media.ops.backend.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class PlaySearchRequestBean {
	@ApiModelProperty(value = "直播员ID")
    private Integer playerId;
	@ApiModelProperty(value = "状态(0,未开放；1,预告中；2,直播中；3,直播结束)")
    private Integer status;
	
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
	
	
}
