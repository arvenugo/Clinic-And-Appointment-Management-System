package com.airtribe.meditrack.service;


import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.DoctorSpecialization;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.DataStoreRegistry;
import com.airtribe.meditrack.util.Validator;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DoctorService implements Searchable<Doctor> {

    /**
     * Store doctor details in the map , ID is the key
     */
	static DataStore<Doctor> doctorData = DataStoreRegistry.getDoctorStore();

    /**
     * Create a doctor and add it to cache
     *
     * @param name
     * @param age
     * @param hospName
     * @param spec
     * @param fees
     * @return
     */
    public Doctor createDoctor(String name, int age, DoctorSpecialization spec, double fees,int yearsOfExperince, String qualification) throws InvalidDataException {
        Validator.validateName(name);
        Validator.validateAge(age);

        Doctor doctor = new Doctor(name, age, spec, fees,yearsOfExperince,qualification);
        doctorData.save(doctor);

        return doctor;
    }

    /**
     * Get Doctor by ID
     */
    public Doctor getDoctorById(int id) {
        Optional<Doctor> doctor = doctorData.findById(id);

        if (doctor.isEmpty()) {
            throw new InvalidDataException("Doctor not found with id: " + id);
        }

        return doctor.get();
    }

    /**
     * Get All Doctors
     */
    public List<Doctor> getAllDoctors() {
        return doctorData.findAll();
    }

    /**
     * Update Doctor
     */
    public Doctor updateDoctor(int id, Doctor updatedDoctor) {

        Doctor existing = getDoctorById(id);
        doctorData.save(existing);

        return existing;
    }

    /**
     * Delete Doctor
     */
    public void deleteDoctor(int id) {
        if (doctorData.findById(id).isEmpty()) {
            throw new InvalidDataException("Cannot delete. Doctor not found: " + id);
        }
        Doctor doctor = doctorData.findAll().stream().filter(d -> d.getId() == id).findFirst().orElse(null);
        if(doctor !=null && doctor.getListOfAppointments().stream().anyMatch(a -> a.getApptTime().isAfter(LocalDateTime.now()))) {
        	throw new InvalidDataException("Doctor has future appointments, cannot delete now " + id);
        }
       

        doctorData.delete(id);
    }

    /**
     * Search by ID (Overloading)
     */
    public Doctor searchDoctor(int id) {
        return getDoctorById(id);
    }

    /**
     * Search by Name (Overloading)
     */
    public List<Doctor> searchDoctor(String name) {
        return doctorData.findAll()
                .stream()
                .filter(d -> d.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    /**
     * Search by Specialization
     */
    public List<Doctor> searchDoctor(DoctorSpecialization specialization) {
        return doctorData.findAll()
                .stream()
                .filter(d -> d.getSpecialization() == specialization)
                .collect(Collectors.toList());
    }


    /**
     * Generic Search (Interface implementation)
     */
    @Override
    public List<Doctor> search(String keyword) {
        return doctorData.findAll()
                .stream()
                .filter(d -> d.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Get Average Consultation Fee
     */
    public double getAverageConsultationFee() {
        return doctorData.findAll()
                .stream()
                .mapToDouble(Doctor::getConsultationFees)
                .average()
                .orElse(0.0);
    }

    /**
     * Group Doctors by Specialization
     */
    public Map<DoctorSpecialization, List<Doctor>> groupBySpecialization() {
        return doctorData.findAll()
                .stream()
                .collect(Collectors.groupingBy(Doctor::getSpecialization));
    }

    /**
     * Search for a list of docs having lesser than minimum num of apts
     */
    public List<Doctor> groupByNumOfApts(int minNumOfApts) {
        return doctorData.findAll()
                .stream()
                .filter(d -> d.getNumOfApts() <= minNumOfApts)
                .collect(Collectors.toList());
    }


}