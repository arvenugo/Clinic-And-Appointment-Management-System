package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class responsible for generating patient ids
 */
public class IDGenerator {

    /**
     * counter obj for id
     */
    private static final AtomicInteger counter = new AtomicInteger(0);

    /**
     * Generate the patient id
     *
     * @return int
     */
    public static int generateId() {
        return counter.incrementAndGet(); // thread-safe increment
    }
}
