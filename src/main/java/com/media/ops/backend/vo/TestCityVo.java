package com.media.ops.backend.vo;

import java.util.List;
/*
   省、市、区、楼宇树形结构
 */
public class TestCityVo {
    private String id;//市区id
    private String label;//名称
    private List<TestAreaVo> children;//县区

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

    public List<TestAreaVo> getChildren() {
        return children;
    }

    public void setChildren(List<TestAreaVo> children) {
        this.children = children;
    }
}
