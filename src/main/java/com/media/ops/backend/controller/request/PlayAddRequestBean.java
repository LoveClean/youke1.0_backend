package com.media.ops.backend.controller.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class PlayAddRequestBean {
	@ApiModelProperty(value = "标题")
    private String title;
	@ApiModelProperty(value = "类型：0普通直播；1插播")
    private Integer type;
	@NotNull(message = "直播员不能为空")
	@ApiModelProperty(value = "直播员ID", required = true)
    private Integer playerid;
	@ApiModelProperty(value = "封面图片名")
    private String picpath;
    
    private Date begintime;
    
    private Date endtime;
	
	@ApiModelProperty(value = "推流地址")
    private String streamaddress;
	@ApiModelProperty(value = "播放地址")
    private String playaddress;
	@ApiModelProperty(value = "直播地点")
    private String realaddress;
	@ApiModelProperty(value = "状态(0,未开放；1,预告中；2,直播中；3,直播结束)")
    private Integer status;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPlayerid() {
		return playerid;
	}
	public void setPlayerid(Integer playerid) {
		this.playerid = playerid;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	public String getStreamaddress() {
		return streamaddress;
	}
	public void setStreamaddress(String streamaddress) {
		this.streamaddress = streamaddress;
	}
	public String getPlayaddress() {
		return playaddress;
	}
	public void setPlayaddress(String playaddress) {
		this.playaddress = playaddress;
	}
	public String getRealaddress() {
		return realaddress;
	}
	public void setRealaddress(String realaddress) {
		this.realaddress = realaddress;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
