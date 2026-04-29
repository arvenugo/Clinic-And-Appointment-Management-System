package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class responsible for generating  ids
 */
public class IDGenerator {

	/**
	 * singleton instance
	 */
    private static final IdGenerator instance = new IdGenerator();

    /**
     * counter obj for patient id
     */
    private AtomicInteger patientIdCounter = new AtomicInteger(0);

    /**
     * counter obj for appt id
     */
    private  AtomicInteger apptIdCounter = new AtomicInteger(0);

    // Private constructor
    private IdGenerator() {
        patientIdCounter = new AtomicInteger(0);
        appointmentIdCounter = new AtomicInteger(0);
    }
 // Global access
    public static IdGenerator getInstance() {
        return instance;
    }

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
