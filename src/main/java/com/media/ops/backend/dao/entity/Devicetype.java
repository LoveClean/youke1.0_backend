package com.media.ops.backend.dao.entity;

public class Devicetype {
    private Integer id;

    private String name;

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

	public Devicetype(String name) {
		super();
		this.name = name;
	}

	public Devicetype() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}