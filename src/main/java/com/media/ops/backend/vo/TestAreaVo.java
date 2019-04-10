package com.media.ops.backend.vo;

import java.util.List;
/*
   省、市、区、楼宇树形结构
 */
public class TestAreaVo {
    private String id;//县区id
    private String label;//名称
    private List<TestBuildingVo> children;//楼宇

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

    public List<TestBuildingVo> getChildren() {
        return children;
    }

    public void setChildren(List<TestBuildingVo> children) {
        this.children = children;
    }
}
