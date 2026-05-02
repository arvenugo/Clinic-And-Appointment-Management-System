package com.airtribe.meditrack.util;


import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.InvalidDataException;

import java.util.regex.Pattern;

/**
 * Utility class for validating input data across the application.
 */
public final class Validator {

    private Validator() {
        // prevent instantiation
    }

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]{2,50}$");


    /**
     * Validates name
     */
    public static boolean validateName(String name) {
        if (name == null || !NAME_PATTERN.matcher(name).matches()) {
            return false;
        }
        return true;
    }

    /**
     * Validates age
     */
    public static boolean validateAge(int age) {
        if (age <= 0 || age > 120) {
            return false;
        }
        return true;
    }


    /**
     * Validates non-null and non-empty string
     */
    public static boolean validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Validates positive number
     */
    public static void validatePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new InvalidDataException(fieldName + " must be positive.");
        }
    }
    
    public boolean validateDoctor(Doctor doctor) {
    	if(doctor !=null && validateAge(doctor.getAge()) && validateName(doctor.getName())
    			&& doctor.getConsultationFees() > 0) {
    		return true;
    	}
    	return false;
    	
    }
    
    public boolean validatePatient(Patient patient) {
    	if(patient !=null && validateAge(patient.getAge()) && validateName(patient.getName())
    			) {
    		return true;
    	}
    	return false;
    }


}
