package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.DoctorSpecialization;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Doctor
 */
public class Doctor extends Person {

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
     * Constr.
     *
     * @param name
     * @param age
     * @param hospitalName
     * @param specialization
     * @param listOfAppointments
     */
    public Doctor(String name, int age, String hospitalName, DoctorSpecialization specialization,
                  List<Appointment> listOfAppointments) {
        super(name, age);
        this.hospitalName = hospitalName;
        this.specialization = specialization;
        this.listOfAppointments = listOfAppointments;
    }


    /**
     * Return the list of appointments of the patient
     *
     * @return List<Appointment>
     */
    public List<Appointment> listOfAppointments() {
        return listOfAppointments;
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
    public String getSpecialization() {
        return specialization.toString();
    }


}
