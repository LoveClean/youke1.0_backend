package com.media.ops.backend.controller.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class BuildingFloorUptRequestBean {
	@NotNull(message = "Id不能为空")
	@ApiModelProperty(value = "Id，必填", required = true)    
	private Integer id;

	@NotNull(message = "楼宇Id不能为空")
	@ApiModelProperty(value = "楼宇Id，必填", required = true)
    private Integer buildingid;

	@NotNull(message = "楼层不能为空")
	@ApiModelProperty(value = "楼层，必填", required = true)	
    private Integer floorno;
    
	@ApiModelProperty(value = "平面图path")
    private String path;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBuildingid() {
		return buildingid;
	}

	public void setBuildingid(Integer buildingid) {
		this.buildingid = buildingid;
	}

	public Integer getFloorno() {
		return floorno;
	}

	public void setFloorno(Integer floorno) {
		this.floorno = floorno;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
    
}
