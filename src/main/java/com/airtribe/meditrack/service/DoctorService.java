package com.airtribe.meditrack.service;


import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.enums.DoctorSpecialization;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.Validator;


import java.util.*;
import java.util.stream.Collectors;

public class DoctorService implements Searchable<Doctor> {

    /**
     * Store doctor details in the map , ID is the key
     */
    private Map<Integer, Doctor> doctorMap = new HashMap<>();

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
    public Doctor createDoctor(String name, int age, String hospName, DoctorSpecialization spec, double fees) throws InvalidDataException {
        Validator.validateName(name);
        Validator.validateAge(age);
       Validator.validateName(hospName);

        Doctor doctor = new Doctor(name, age, hospName, spec, fees);
        doctorMap.put(doctor.getDoctorId(), doctor);

        return doctor;
    }

    /**
     * Get Doctor by ID
     */
    public Doctor getDoctorById(int id) {
        Doctor doctor = doctorMap.get(id);

        if (doctor == null) {
            throw new InvalidDataException("Doctor not found with id: " + id);
        }

        return doctor;
    }

    /**
     * Get All Doctors
     */
    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctorMap.values());
    }

    /**
     * Update Doctor
     */
    public Doctor updateDoctor(int id, Doctor updatedDoctor) {

        Doctor existing = getDoctorById(id);


        existing.setName(updatedDoctor.getName());
        existing.setAge(updatedDoctor.getAge());
        existing.setSpecialization(updatedDoctor.getSpecialization());
        existing.setHospitalName(updatedDoctor.getHospitalName());
        existing.setConsultationFees(updatedDoctor.getConsultationFees());
        return existing;
    }

    /**
     * Delete Doctor
     */
    public void deleteDoctor(int id) {
        if (!doctorMap.containsKey(id)) {
            throw new InvalidDataException("Cannot delete. Doctor not found: " + id);
        }

        doctorMap.remove(id);
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
        return doctorMap.values()
                .stream()
                .filter(d -> d.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    /**
     * Search by Specialization
     */
    public List<Doctor> searchDoctor(DoctorSpecialization specialization) {
        return doctorMap.values()
                .stream()
                .filter(d -> d.getSpecialization() == specialization)
                .collect(Collectors.toList());
    }


    /**
     * Generic Search (Interface implementation)
     */
    @Override
    public List<Doctor> search(String keyword) {
        return doctorMap.values()
                .stream()
                .filter(d -> d.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Get Average Consultation Fee
     */
    public double getAverageConsultationFee() {
        return doctorMap.values()
                .stream()
                .mapToDouble(Doctor::getConsultationFees)
                .average()
                .orElse(0.0);
    }

    /**
     * Group Doctors by Specialization
     */
    public Map<DoctorSpecialization, List<Doctor>> groupBySpecialization() {
        return doctorMap.values()
                .stream()
                .collect(Collectors.groupingBy(Doctor::getSpecialization));
    }

    /**
     * Search for a list of docs having lesser than minimum num of apts
     */
    public List<Doctor> groupByNumOfApts(int minNumOfApts) {
        return doctorMap.values()
                .stream()
                .filter(d -> d.getNumOfApts() <= minNumOfApts)
                .collect(Collectors.toList());
    }


}