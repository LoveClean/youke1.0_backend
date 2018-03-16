package com.media.ops.backend.vo;


public class AdMaterialVo {
	private int id;  //广告素材ID
	private int adId;  //广告ID
	private int materialId;  //素材ID
	private String materialName;  //素材名称
	private String materialType;   //素材类型
	private String materialPath;   //素材地址
	
	private int orderIndex; //播放顺序
	private int loadStep;   //加载间隔
	private int displayTime;  //显示时间
	private String musicPath;  //伴音地址 

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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

	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getMaterialPath() {
		return materialPath;
	}
	public void setMaterialPath(String materialPath) {
		this.materialPath = materialPath;
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
