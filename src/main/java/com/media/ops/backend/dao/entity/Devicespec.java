package com.media.ops.backend.dao.entity;

public class Devicespec {
    private String spec;

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    
    
	public Devicespec() {

	}

	public Devicespec(String spec) {
		super();
		this.spec = spec;
	}
    
    
    
}