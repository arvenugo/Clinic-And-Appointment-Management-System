package com.airtribe.meditrack.enums;

public enum AppointmentStatus {

    CONFIRMED,
    CANCELLED,
    PENDING;
	
	public static AppointmentStatus parseGender(String input) {
        try {
            return AppointmentStatus.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid gender: " + input);
        }
    }

}
