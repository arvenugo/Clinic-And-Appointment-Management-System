package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.AppointmentObserver;

public class PatientNotification implements AppointmentObserver {

    /**
     * The patient entity
     */
    private Patient patient;

    /**
     * Constr.
     * @param patient
     */
    public PatientNotification(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void update(String message, Appointment appointment) {
        System.out.println("Notify " + patient.getName() + ": " + message);
    }
}
