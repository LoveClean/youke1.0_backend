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
	@ApiModelProperty(value = "直播开始时间")
    private Date begintime;
	@ApiModelProperty(value = "直播结束时间")
    private Date endtime;
	@ApiModelProperty(value = "直播地点")
    private String realaddress;
	
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

	public String getRealaddress() {
		return realaddress;
	}
	public void setRealaddress(String realaddress) {
		this.realaddress = realaddress;
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
