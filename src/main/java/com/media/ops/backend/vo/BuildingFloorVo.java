package com.media.ops.backend.vo;

import java.util.List;

public class BuildingFloorVo {

    private Integer buildingid;  //楼宇Id
    private Integer floorno;  //楼层id
    private String path;   //楼层平面图
    private List<FloorDeviceListVo> floorDeviceListVoList;   //楼层设备列表
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
	public List<FloorDeviceListVo> getFloorDeviceListVoList() {
		return floorDeviceListVoList;
	}
	public void setFloorDeviceListVoList(List<FloorDeviceListVo> floorDeviceListVoList) {
		this.floorDeviceListVoList = floorDeviceListVoList;
	}
    
    
    
    
}
