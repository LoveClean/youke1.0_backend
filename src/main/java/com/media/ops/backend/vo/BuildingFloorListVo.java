package com.media.ops.backend.vo;

import java.util.List;

public class BuildingFloorListVo {
	
	private Integer buildingid;  //楼宇Id
	
	private List<BuildingFloorListVo> buildingFloorListVoList;  //楼宇楼层列表

	public Integer getBuildingid() {
		return buildingid;
	}

	public void setBuildingid(Integer buildingid) {
		this.buildingid = buildingid;
	}

	public List<BuildingFloorListVo> getBuildingFloorListVoList() {
		return buildingFloorListVoList;
	}

	public void setBuildingFloorListVoList(List<BuildingFloorListVo> buildingFloorListVoList) {
		this.buildingFloorListVoList = buildingFloorListVoList;
	}
	
	

}
