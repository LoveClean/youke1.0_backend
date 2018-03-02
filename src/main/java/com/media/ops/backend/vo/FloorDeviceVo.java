package com.media.ops.backend.vo;

public class FloorDeviceVo {
	private int floorId;   //楼层id,可以去掉
	private DeviceVo deviceVo;  //设备模型
    private float x;   //X坐标
    private float y;   //Y坐标
	public int getFloorId() {
		return floorId;
	}
	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}
	public DeviceVo getDeviceVo() {
		return deviceVo;
	}
	public void setDeviceVo(DeviceVo deviceVo) {
		this.deviceVo = deviceVo;
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
