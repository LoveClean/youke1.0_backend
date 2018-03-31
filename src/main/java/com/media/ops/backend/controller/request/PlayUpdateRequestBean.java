package com.media.ops.backend.controller.request;


import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class PlayUpdateRequestBean {
	@NotNull(message = "id不能为空")
	@ApiModelProperty(value = "id，必填", required = true)
    private Integer id;
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
	@ApiModelProperty(value = "状态(0,未开放；1,预告中；2,直播中；3,直播结束)")
    private Integer status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPlayerid() {
		return playerid;
	}
	public void setPlayerid(Integer playerid) {
		this.playerid = playerid;
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
