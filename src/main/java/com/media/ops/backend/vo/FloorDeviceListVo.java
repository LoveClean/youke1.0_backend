package com.media.ops.backend.vo;

import java.util.List;

public class FloorDeviceListVo {
	private int floorId;   //楼层id
	private List<FloorDeviceVo> floorDeviceVoList;  //该楼层的设备列表
	public int getFloorId() {
		return floorId;
	}
	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}
	public List<FloorDeviceVo> getFloorDeviceVoList() {
		return floorDeviceVoList;
	}
	public void setFloorDeviceVoList(List<FloorDeviceVo> floorDeviceVoList) {
		this.floorDeviceVoList = floorDeviceVoList;
	}
	
	
}
