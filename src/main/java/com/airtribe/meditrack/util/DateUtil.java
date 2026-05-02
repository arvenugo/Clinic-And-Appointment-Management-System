package com.airtribe.meditrack.util;


import com.airtribe.meditrack.exception.InvalidDataException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for date operations
 */
public final class DateUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DateUtil() {
        // prevent instantiation
    }

    /**
     * Parse string to LocalDate
     */
    public static LocalDateTime parseDate(String dateStr) {
        try {
            return LocalDateTime.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDataException("Invalid date format. Use yyyy-MM-dd");
        }
    }

    /**
     * Format LocalDate to string
     */
    public static String formatDate(LocalDate date) {
        return date.format(FORMATTER);
    }

    /**
     * Validate date is not in the past
     */
    public static boolean validateFutureOrToday(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            return true;
        } else
            return false;
    }


    /**
     * Validate date time is not in the past
     */
    public static boolean validateFutureOrToday(LocalDateTime date) {
        if (date.isBefore(LocalDateTime.now())) {
            return true;
        } else
            return false;
    }


    /**
     * Get today's date
     */
    public static LocalDate getToday() {
        return LocalDate.now();
    }
}
