package com.media.ops.backend.vo;

import java.util.List;

public class AdMaterialListVo {
	
	private int id;  
	private String adId;
	private List<AdMaterialVo> adMaterialVoList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public List<AdMaterialVo> getAdMaterialVoList() {
		return adMaterialVoList;
	}
	public void setAdMaterialVoList(List<AdMaterialVo> adMaterialVoList) {
		this.adMaterialVoList = adMaterialVoList;
	}
	
	

}
