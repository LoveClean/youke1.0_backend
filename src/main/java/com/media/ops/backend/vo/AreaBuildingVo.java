package com.media.ops.backend.vo;

public class AreaBuildingVo {
    private Integer id;    //楼宇id
    
    private String name;   //楼宇名称

    private String areaid;   //县区id

    private String address;   //详细地址

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
    
}
