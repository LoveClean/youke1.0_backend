package com.media.ops.backend.vo;

import java.util.List;

public class MaterialGroupVo {
    private Integer id;   //分组编号

    private String name;  //分组名称
    
    private List<MaterialVo> materialVoList;   //该分组下的素材

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

	public List<MaterialVo> getMaterialVoList() {
		return materialVoList;
	}

	public void setMaterialVoList(List<MaterialVo> materialVoList) {
		this.materialVoList = materialVoList;
	}
	
	
    
    
}
