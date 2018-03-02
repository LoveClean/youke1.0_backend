package com.media.ops.backend.vo;

public class DeviceGroupVo {

    private Integer id;   //分组编号

    private String name;  //分组名称

    private Integer parentid;  //父级编号

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

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
    
    
}
