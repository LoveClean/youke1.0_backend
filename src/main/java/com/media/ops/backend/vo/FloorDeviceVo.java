package com.media.ops.backend.vo;

public class FloorDeviceVo {
	private int id;
	private int floorId;   //楼层id
	//private DeviceVo deviceVo;  //设备模型
	private int deviceTypeId;
	private String deviceTypeName;
    private float x;   //X坐标
    private float y;   //Y坐标
    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFloorId() {
		return floorId;
	}
	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}


	
	public int getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(int deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	public String getDeviceTypeName() {
		return deviceTypeName;
	}
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
    
    
}
