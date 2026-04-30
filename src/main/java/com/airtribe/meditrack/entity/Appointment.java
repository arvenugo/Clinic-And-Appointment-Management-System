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
    private Patient patient;

    /**
     * The appt time
     */
    private LocalDateTime apptTime;

    /**
     * The appt id
     */
    private final int apptId;
    
    private boolean completed;
    
    private String symptoms;


    /**
     * constr.
     *
     * @param doctor
     * @param patient
     * @param apptTime
     */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime apptTime,String complaints) {
        this.doctor = doctor;
        this.patient = patient;
        this.apptTime = apptTime;
        this.apptId = IDGenerator.generateApptId();
        this.apptStatus = AppointmentStatus.CONFIRMED;
        this.symptoms = complaints;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Appointment cloned = (Appointment) super.clone();

        //  Deep copy
        if (this.patient != null) {
            cloned.patient = (Patient) this.patient.clone();
        }

        if (this.doctor != null) {
            cloned.doctor = (Doctor) this.doctor.clone();
        }

        // LocalDateTime is immutable → safe to assign directly
        cloned.apptTime = this.apptTime;

        // Enum is immutable → safe
        cloned.apptStatus = this.apptStatus;

        return cloned;
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

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * @return the symptoms
	 */
	public String getSymptoms() {
		return symptoms;
	}

	/**
	 * @param symptoms the symptoms to set
	 */
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
    
    


}
