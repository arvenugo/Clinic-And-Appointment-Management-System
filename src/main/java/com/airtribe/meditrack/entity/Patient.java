package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.IDGenerator;

/**
 * Entity class representing Patient
 */
public class Patient extends Person implements Cloneable {


    /**
     * patient id
     */
    private final int patientId;

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
        patientId = IDGenerator.generatePatientId();
    }

    /**
     * Clonable.
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Patient cloned = (Patient) super.clone();

        if (this.currentAppointment != null) {
            cloned.currentAppointment = (Appointment) this.currentAppointment.clone();
        }

        return cloned;
    }

    /**
     * Get the patient id
     *
     * @return int
     */
    public int getPatientId() {
        return patientId;
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
     * Get current appt
     *
     * @return
     */
    public Appointment getCurrentAppointment() {
        return currentAppointment;
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