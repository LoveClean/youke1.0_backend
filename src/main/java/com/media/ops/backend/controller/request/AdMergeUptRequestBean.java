package com.media.ops.backend.controller.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AdMergeUptRequestBean {

	@NotNull(message = "广告Id不能为空")
	@ApiModelProperty(value = "广告Id，必填", required = true)
	private Integer id;
		
	@NotBlank(message = "广告名称不能为空")
	@ApiModelProperty(value = "广告名称name，必填", required = true)
	private String name;  //广告名称
	
	@NotBlank(message = "groupId不能为空")
	@ApiModelProperty(value = "广告分组groudId，必填", required = true)
    private Integer groupid;  //分组id
	
	private  List<AdMaterialUptRequestBean> adData;

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

	public List<AdMaterialUptRequestBean> getAdData() {
		return adData;
	}

	public void setAdData(List<AdMaterialUptRequestBean> adData) {
		this.adData = adData;
	}


	
	
}
