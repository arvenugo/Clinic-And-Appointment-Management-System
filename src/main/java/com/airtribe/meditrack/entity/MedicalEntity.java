package com.airtribe.meditrack.entity;

public abstract class MedicalEntity {
	
	
	protected final Integer id;

    protected MedicalEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
