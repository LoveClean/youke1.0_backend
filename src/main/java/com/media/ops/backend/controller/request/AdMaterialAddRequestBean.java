package com.media.ops.backend.controller.request;

public class AdMaterialAddRequestBean {

	private int adId;  //广告ID
	private int materialId;  //素材ID
	private int orderIndex; //播放顺序
	private int loadStep;   //加载间隔
	private int displayTime;  //显示时间
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
