package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.DoctorSpecialization;
import com.airtribe.meditrack.util.IDGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Doctor
 */
public class Doctor extends Person implements Cloneable {

    /**
     * Name of the hospital which the doc works for
     */
    private String hospitalName;

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


    /**
     * Constr.
     *
     * @param name
     * @param age
     * @param hospitalName
     * @param specialization
     * @param consultationFees
     */
    public Doctor(String name, int age, String hospitalName, DoctorSpecialization specialization, double consultationFees
    ) {
        super(name, age);
        this.hospitalName = hospitalName;
        this.specialization = specialization;
        doctorId = IDGenerator.generateDoctorId();
        this.consultationFees = consultationFees;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Doctor cloned = (Doctor) super.clone();

        cloned.hospitalName = hospitalName;
        cloned.specialization = specialization;

        cloned.consultationFees = consultationFees;
        cloned.listOfAppointments.addAll(this.listOfAppointments);

        return cloned;
    }


    /**
     * Return the list of appointments of the patient
     *
     * @return List<Appointment>
     */
    public List<Appointment> listOfAppointments() {
        return new ArrayList<>(listOfAppointments);
    }


    /**
     * Return the hospital name
     *
     * @return
     */
    public String getHospitalName() {
        return hospitalName;
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
     * Set hospName
     *
     * @param hospitalName
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
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

}
