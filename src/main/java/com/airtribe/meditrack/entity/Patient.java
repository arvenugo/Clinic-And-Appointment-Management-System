package com.airtribe.meditrack.entity;

import java.util.List;

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
    private List<Appointment> appointments;

    

    /**
	 * @param name
	 * @param age
	 */
	public Patient(String name, int age) {
		super(name, age);
		this.patientId = IDGenerator.generatePatientId();
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

        if (this.appointments != null) {
        	cloned.appointments.addAll(this.appointments);

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
	 * @return the appointments
	 */
	public List<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	/**
     * Add an appointment
     *
     * @param apt
     */
    public void addAppointment(Appointment apt) {
    	appointments.add(apt);
    }


	

}