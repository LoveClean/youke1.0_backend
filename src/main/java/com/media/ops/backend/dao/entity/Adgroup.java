package com.media.ops.backend.dao.entity;

public class Adgroup {
	   private Integer id;

	    private String name;

	    private Integer parentid;

	    private Byte status;

	    private Integer sortorder;

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
	        this.name = name == null ? null : name.trim();
	    }

	    public Integer getParentid() {
	        return parentid;
	    }

	    public void setParentid(Integer parentid) {
	        this.parentid = parentid;
	    }

	    public Byte getStatus() {
	        return status;
	    }

	    public void setStatus(Byte status) {
	        this.status = status;
	    }

	    public Integer getSortorder() {
	        return sortorder;
	    }

	    public void setSortorder(Integer sortorder) {
	        this.sortorder = sortorder;
	    }
}