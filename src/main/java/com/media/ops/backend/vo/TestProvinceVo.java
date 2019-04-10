package com.media.ops.backend.vo;

import java.util.List;
/*
   省、市、区、楼宇树形结构
 */
public class TestProvinceVo {
    private String id; //省id
    private String label;//省名称
    private List<TestCityVo> children;//市区

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

    public List<TestCityVo> getChildren() {
        return children;
    }

    public void setChildren(List<TestCityVo> children) {
        this.children = children;
    }
}
