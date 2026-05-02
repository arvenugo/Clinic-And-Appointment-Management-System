package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.DoctorSpecialization;
import com.airtribe.meditrack.util.IDGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Doctor
 */
public class Doctor extends Person implements Cloneable {

    @Override
	public String toString() {
		
		return "Doctor [doctorId=" + doctorId + ", Dr." + super.getName() + qualification + "specializes in "  + specialization +", has " + yearsOfExperince + 
				" years of Experince. Their consulation fees is "+ consultationFees ;
	}

	/**
     * Specialization of the doctor
     */
    private DoctorSpecialization specialization;


    /**
     * List of patient appointments for the doctor
     */
    private List<Appointment> listOfAppointments = new ArrayList<>();

    /**
     * doctor id
     */
    private final int doctorId;

    /**
     * Consultation  fees
     */
    private double consultationFees;
    
    private int yearsOfExperince;
    
    private String qualification;


    /**
     * Constr.
     *
     * @param name
     * @param age
     * @param hospitalName
     * @param specialization
     * @param consultationFees
     */
    public Doctor(String name, int age, DoctorSpecialization specialization, double consultationFees,
    int yearsOfExperince,String qualification) {
        super(name, age);
        this.specialization = specialization;
        doctorId = IDGenerator.generateDoctorId();
        this.consultationFees = consultationFees;
        this.yearsOfExperince = yearsOfExperince;
        this.qualification = qualification;

    }

    /**
	 * @param name
	 * @param age
	 * @param specialization
	 * @param consultationFees
	 * @param yearsOfExperince
	 */
	public Doctor(int doctorId, DoctorSpecialization specialization, double consultationFees,
			int yearsOfExperince) {
		super();
		this.doctorId = doctorId;
		this.specialization = specialization;
		this.consultationFees = consultationFees;
		this.yearsOfExperince = yearsOfExperince;
	}

	@Override
    public Object clone() throws CloneNotSupportedException {
        Doctor cloned = (Doctor) super.clone();
        cloned.specialization = specialization;

        cloned.consultationFees = consultationFees;
        cloned.listOfAppointments.addAll(this.listOfAppointments);
        cloned.yearsOfExperince = yearsOfExperince;

        return cloned;
    }

	

    /**
	 * @return the listOfAppointments
	 */
	public List<Appointment> getListOfAppointments() {
		return listOfAppointments;
	}

	/**
	 * @param listOfAppointments the listOfAppointments to set
	 */
	public void setListOfAppointments(List<Appointment> listOfAppointments) {
		this.listOfAppointments = listOfAppointments;
	}

	/**
     * Return the specialization of the doctor
     *
     * @return String
     */
    public DoctorSpecialization getSpecialization() {
        return specialization;
    }

    /**
     * Add an appointment
     *
     * @param apt
     */
    public void addAppointment(Appointment apt) {
        listOfAppointments.add(apt);
    }

    /**
     * Remove an appointment
     *
     * @param apt
     */
    public void removeAppointment(Appointment apt) {
        listOfAppointments.remove(apt);
    }

    /**
     * Get the doctor ID
     *
     * @return
     */
    public int getDoctorId() {
        return doctorId;
    }

    /**
     * Set specialization
     *
     * @param specialization
     */
    public void setSpecialization(DoctorSpecialization specialization) {
        this.specialization = specialization;
    }


    /**
     * Get fees
     *
     * @return
     */
    public double getConsultationFees() {
        return consultationFees;
    }

    /**
     * Set fees
     *
     * @param consultationFees
     */
    public void setConsultationFees(double consultationFees) {
        this.consultationFees = consultationFees;
    }

    /**
     * Get current num of appointments
     *
     * @return
     */
    public int getNumOfApts() {
        return listOfAppointments.size();
    }

	/**
	 * @return the yearsOfExperince
	 */
	public int getYearsOfExperince() {
		return yearsOfExperince;
	}

	/**
	 * @param yearsOfExperince the yearsOfExperince to set
	 */
	public void setYearsOfExperince(int yearsOfExperince) {
		this.yearsOfExperince = yearsOfExperince;
	}
    
    

}
