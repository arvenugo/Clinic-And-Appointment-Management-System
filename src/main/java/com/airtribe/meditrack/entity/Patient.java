package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.IDGenerator;

/**
 * Entity class representing Patient
 */
public class Patient extends Person implements Cloneable {


    /**
     * patient id
     */
    private final int id;

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
    public Patient(String name, int age, Appointment currentAppointment) {
        super(name, age);
        this.id = IDGenerator.generatePatientId();
        this.currentAppointment = currentAppointment;
    }

    /**
     * Get the patient id
     *
     * @return int
     */
    public int getPatientId() {
        return id;
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
