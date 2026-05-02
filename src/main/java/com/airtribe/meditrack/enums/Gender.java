package com.airtribe.meditrack.enums;

public enum Gender {
	
	MALE("M"),FEMALE("F"),OTHERS("O");
	
	private final String code;

    // Constructor (always private or package-private)
    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
