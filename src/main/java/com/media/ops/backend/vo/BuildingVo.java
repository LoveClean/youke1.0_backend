package com.media.ops.backend.vo;

import java.util.List;

public class BuildingVo {
	
    private Integer id;    //楼宇id
 
    private String name;   //楼宇名称

    private String areaid;   //县区id
    private ProvinceCityAreaVo provinceCityAreaVo;   //区域对象模型 

    private String address;   //详细地址
    
    private List<BuildingFloorListVo> buildingFloorListVoList;   //楼层列表

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

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public ProvinceCityAreaVo getProvinceCityAreaVo() {
		return provinceCityAreaVo;
	}

	public void setProvinceCityAreaVo(ProvinceCityAreaVo provinceCityAreaVo) {
		this.provinceCityAreaVo = provinceCityAreaVo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<BuildingFloorListVo> getBuildingFloorListVoList() {
		return buildingFloorListVoList;
	}

	public void setBuildingFloorListVoList(List<BuildingFloorListVo> buildingFloorListVoList) {
		this.buildingFloorListVoList = buildingFloorListVoList;
	}
    
    
    
    
   
    
	

}
