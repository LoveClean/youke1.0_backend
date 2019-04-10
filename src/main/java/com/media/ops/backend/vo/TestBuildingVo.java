package com.media.ops.backend.vo;
/*
   省、市、区、楼宇树形结构
 */
public class TestBuildingVo {
    private String id;//楼宇编号
    private String label;//名称


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
