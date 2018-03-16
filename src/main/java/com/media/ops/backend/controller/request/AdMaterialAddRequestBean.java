package com.media.ops.backend.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AdMaterialAddRequestBean {

	@NotBlank(message = "adId不能为空")
	@ApiModelProperty(value = "广告Id，必填", required = true)
	private int adId;  //广告ID
	
	@NotBlank(message = "materialId不能为空")
	@ApiModelProperty(value = "素材Id，必填", required = true)
	private int materialId;  //素材ID
	
	@ApiModelProperty(value = "播放顺序orderIndex")
	private int orderIndex; //播放顺序
	@ApiModelProperty(value = "加载间隔loadStep")
	private int loadStep;   //加载间隔
	@ApiModelProperty(value = "播放时间displayTime")
	private int displayTime;  //显示时间
	@ApiModelProperty(value = "伴音地址musicPath")
	private String musicPath;  //伴音地址 
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	public int getLoadStep() {
		return loadStep;
	}
	public void setLoadStep(int loadStep) {
		this.loadStep = loadStep;
	}
	public int getDisplayTime() {
		return displayTime;
	}
	public void setDisplayTime(int displayTime) {
		this.displayTime = displayTime;
	}
	public String getMusicPath() {
		return musicPath;
	}
	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
	
	
}
