package com.media.ops.backend.vo;

import com.media.ops.backend.dao.entity.Ad;

import java.util.List;

public class AdMaterialListVo extends AddeliveryVo {
	
	private List<AdMaterialVo> adMaterialVoList;
	public List<AdMaterialVo> getAdMaterialVoList() {
		return adMaterialVoList;
	}
	public void setAdMaterialVoList(List<AdMaterialVo> adMaterialVoList) {
		this.adMaterialVoList = adMaterialVoList;
	}
	
	

}
