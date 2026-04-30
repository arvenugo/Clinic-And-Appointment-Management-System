package com.airtribe.meditrack.util;


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
    public static void validateName(String name) {
        if (name == null || !NAME_PATTERN.matcher(name).matches()) {
            throw new InvalidDataException("Invalid name. Only alphabets and spaces allowed (2-50 chars).");
        }
    }

    /**
     * Validates age
     */
    public static void validateAge(int age) {
        if (age <= 0 || age > 120) {
            throw new InvalidDataException("Invalid age. Must be between 1 and 120.");
        }
    }


    /**
     * Validates non-null and non-empty string
     */
    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidDataException(fieldName + " cannot be empty.");
        }
    }

    /**
     * Validates positive number
     */
    public static void validatePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new InvalidDataException(fieldName + " must be positive.");
        }
    }


}
