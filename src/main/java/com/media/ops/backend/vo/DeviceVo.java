package com.media.ops.backend.vo;

public class DeviceVo {
 
    private String code;    //设备编号 
    private String mac;     //设备物理地址
    
    private String type;
    private Integer groupid;  //设备所属分组
    
    private String brand;    //设备品牌
    private String spec;    //设备规格
    
    private String areaid;   //保留？？
    //private ProvinceCityAreaVo provinceCityAreaVo;  //区域对象模型
    
    private Integer buildingid;  //楼宇id，还是楼层buildingFloorId?
    private String address;   //设备区域地址，这字段要么？
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public Integer getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(Integer buildingid) {
		this.buildingid = buildingid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
    
}
