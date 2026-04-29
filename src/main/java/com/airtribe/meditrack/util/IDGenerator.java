package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class responsible for generating  ids
 */
public class IDGenerator {

    /**
     * counter obj for patient id
     */
    private static final AtomicInteger patientIdCounter = new AtomicInteger(0);

    /**
     * counter obj for appt id
     */
    private static final AtomicInteger apptIdCounter = new AtomicInteger(0);

    /**
     * counter obj for doctor id
     */
    private static final AtomicInteger docIdCounter = new AtomicInteger(0);

    /**
     * Generate the patient id
     *
     * @return int
     */
    public static int generatePatientId() {
        return patientIdCounter.incrementAndGet(); // thread-safe increment
    }


    /**
     * Generate the appt id
     *
     * @return int
     */
    public static int generateApptId() {
        return patientIdCounter.incrementAndGet(); // thread-safe increment
    }


    /**
     * Generate the appt id
     *
     * @return int
     */
    public static int generateDoctorId() {
        return docIdCounter.incrementAndGet(); // thread-safe increment
    }
}
