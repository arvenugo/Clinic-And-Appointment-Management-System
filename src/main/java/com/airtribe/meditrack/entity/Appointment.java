package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.util.IDGenerator;

import java.time.LocalDateTime;

/**
 * Entity class representing appointment
 */
public class Appointment implements Cloneable {


    /**
     * Current appointment status
     */
    private AppointmentStatus apptStatus;

    /**
     * Assigned doctor for the appointment
     */
    private Doctor doctor;

    /**
     * Patient
     */
    private final Patient patient;

    /**
     * The appt time
     */
    private LocalDateTime apptTime;

    /**
     * The appt id
     */
    private final int apptId;


    /**
     * constr.
     *
     * @param doctor
     * @param patient
     * @param apptTime
     */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime apptTime) {
        this.doctor = doctor;
        this.patient = patient;
        this.apptTime = apptTime;
        this.apptId = IDGenerator.generateApptId();
        this.apptStatus = AppointmentStatus.CONFIRMED;
    }


    /**
     * Get the appt status
     *
     * @return
     */
    public AppointmentStatus getApptStatus() {
        return apptStatus;
    }

    /**
     * Set appt status
     *
     * @param apptStatus
     *
     */
    public void setApptStatus(AppointmentStatus apptStatus) {
        this.apptStatus = apptStatus;
    }

    /**
     * Update appt time
     *
     * @param apptTime
     */
    public void updateApptTime(LocalDateTime apptTime) {
        this.apptTime = apptTime;
    }

    /**
     * Get the appt ID
     *
     * @return
     */
    public int getApptId() {
        return apptId;
    }

    /**
     * Get the doctor
     *
     * @return
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Get the patient
     *
     * @return
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Get the appt time
     *
     * @return
     */
    public LocalDateTime getApptTime() {
        return apptTime;
    }


}
