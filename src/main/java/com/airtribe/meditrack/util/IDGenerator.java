package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class responsible for generating  ids
 */
public class IDGenerator {

	/**
	 * singleton instance
	 */
    private static final IDGenerator instance = new IDGenerator();

    /**
     * counter obj for patient id
     */
    private static AtomicInteger patientIdCounter = new AtomicInteger(0);

    /**
     * counter obj for appt id
     */
    private  static AtomicInteger apptIdCounter = new AtomicInteger(0);
    
    private  static AtomicInteger billIdCounter = new AtomicInteger(0);

    // Private constructor
    private IDGenerator() {
        patientIdCounter = new AtomicInteger(0);
        apptIdCounter = new AtomicInteger(0);
        billIdCounter = new AtomicInteger(0);
    }
 // Global access
    public static IDGenerator getInstance() {
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
    public  static int generateDoctorId() {
        return docIdCounter.incrementAndGet(); // thread-safe increment
    }
    
    public int generateBillId() {
        return billIdCounter.incrementAndGet(); // thread-safe increment
    }
}
