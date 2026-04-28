package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.AppointmentObserver;
import com.airtribe.meditrack.util.IDGenerator;

/**
 * Entity class representing Patient
 */
public class Patient extends Person implements Cloneable {


    /**
     * patient id
     */
    private final int pateintId;

    /**
     * Current appointment
     */
    private Appointment currentAppointment;


    /**
     * Constr.
     *
     * @param name
     * @param age
     */
    public Patient(String name, int age) {
        super(name, age);
        pateintId = IDGenerator.generatePatientId();
    }

    /**
     * Get the patient id
     *
     * @return int
     */
    public int getPatientId() {
        return pateintId;
    }

    /**
     * Set current appointment
     *
     * @param appt
     */
    public void setCurrentAppointment(Appointment appt) {
        currentAppointment = appt;
    }

    /**
     * Get the status of current patient appt
     *
     * @return String
     */
    public String getCurrApptStatus() {
        return currentAppointment.getApptStatus().toString();
    }


}
