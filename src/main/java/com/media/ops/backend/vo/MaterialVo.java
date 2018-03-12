package com.media.ops.backend.vo;

public class MaterialVo {
    private Integer id;  //素材id

    private String name;  //素材名称

    private String type;  //素材类型

    private String path;  //素材地址

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
    
}
