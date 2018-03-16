package com.media.ops.backend.vo;

import java.util.List;

public class AdVo {

    private Integer id;  //广告ID

    private String name;  //广告名称

    private Integer groupid;  //分组id
    
    private String groupName; //分组名称
    
    private List<AdMaterialVo> adMaterialVos; //广告的素材列表

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<AdMaterialVo> getAdMaterialVos() {
		return adMaterialVos;
	}

	public void setAdMaterialVos(List<AdMaterialVo> adMaterialVos) {
		this.adMaterialVos = adMaterialVos;
	}
    
    
    
}
