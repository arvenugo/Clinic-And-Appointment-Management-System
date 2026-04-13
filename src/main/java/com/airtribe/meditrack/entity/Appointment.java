package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;

/**
 * Entity class representing appointment
 */
public class Appointment {


    /**
     * Current appointment status
     */
    private AppointmentStatus apptStatus;


    /**
     * Get the appt status
     * @return
     */
    public AppointmentStatus getApptStatus() {
        return apptStatus;
    }
}
