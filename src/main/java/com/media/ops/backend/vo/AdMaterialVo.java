package com.media.ops.backend.vo;


public class AdMaterialVo {
	private int materialId;  //素材ID
	private int orderIndex; //播放顺序
	private int loadStep;   //加载间隔
	private int displayTime;  //显示时间
	private String musicPath;  //伴音地址 
	private String name;  //素材名称
	private String type;   //素材类型
	private String path;   //素材地址
	
//	public AdMaterialVo(int materialId, int orderIndex, int loadStep, int displayTime, String musicPath, String name,
//			String type, String path) {
//		this.materialId = materialId;
//		this.orderIndex = orderIndex;
//		this.loadStep = loadStep;
//		this.displayTime = displayTime;
//		this.musicPath = musicPath;
//		this.name = name;
//		this.type = type;
//		this.path = path;
//	}
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
