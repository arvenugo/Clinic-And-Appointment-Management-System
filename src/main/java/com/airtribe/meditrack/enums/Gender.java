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
    
    public static Gender parseGender(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Gender input cannot be null or empty");
        }

        String value = input.trim().toUpperCase();

        for (Gender gender : Gender.values()) {
            if (gender.getCode().equalsIgnoreCase(value)) {
                return gender;
            }
        }

        throw new IllegalArgumentException("Invalid gender code: " + input);
    }

}
