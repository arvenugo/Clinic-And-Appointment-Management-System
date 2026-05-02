package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.airtribe.meditrack.enums.Gender;
import com.airtribe.meditrack.util.IDGenerator;

/**
 * Entity class representing Patient
 */
public class Patient extends Person implements Cloneable{


   
    /**
     * Current appointment
     */
    private List<Appointment> appointments = new ArrayList<>();;
    
    private LocalDateTime registrationDate;
    
    private Gender gender;

    

    /**
	 * @param name
	 * @param age
	 */
	public Patient(String name, int age, Gender gender) {
		super(name, age, IDGenerator.generatePatientId());
		this.gender = gender;
		this.registrationDate = LocalDateTime.now();
	}
	
	

    /**
	 * @param name
	 * @param age
	 * @param patientId
	 */
	public Patient(int patientId,String name, int age) {
		super(name, age,patientId);
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
        cloned.registrationDate = registrationDate;
        cloned.gender = gender;

        return cloned;
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

	/**
	 * If patiend ID is the same , it is considered to be same
	 * @param o   the reference object with which to compare.
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Patient)) return false;
		Patient patient = (Patient) o;
		return super.getId() == patient.getId();
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(getId());
	}

	

}