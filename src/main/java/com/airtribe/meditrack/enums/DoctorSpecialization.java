package com.airtribe.meditrack.enums;

public enum DoctorSpecialization {

	PEDIATRICIAN, CARDIOLOGIST, GENERAL_PHYSICIAN, GYNECOLOGIST, DENTIST,
    ORTHOPEDIC, NEUROLOGIST, ENT_SPECIALIST, DERMATOLOGIST;
    
    
    public static DoctorSpecialization parseEnum(String input) {
        try {
            return DoctorSpecialization.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid gender: " + input);
        }
    }
    
}
