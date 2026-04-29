package com.airtribe.meditrack.service;


import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.Searchable;


import java.util.*;
import java.util.stream.Collectors;

public class PatientService implements Searchable<Patient> {

    // In-memory storage
    private Map<Integer, Patient> patientMap = new HashMap<>();

    /**
     * Add Patient
     */
    public Patient createPatient(String name, int age) {


        Patient patient = new Patient(name, age);
        patientMap.put(patient.getPatientId(), patient);
        return patient;
    }

    /**
     * Get Patient by ID
     */
    public Patient getPatientById(int id) {
        Patient patient = patientMap.get(id);

        if (patient == null) {
            throw new InvalidDataException("Patient not found with id: " + id);
        }

        return patient;
    }

    /**
     * Get All Patients
     */
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientMap.values());
    }

    /**
     * Update Patient
     */
    public Patient updatePatient(int id, Patient updatedPatient) {

        Patient existing = getPatientById(id);

        if (existing == null) {
            throw new InvalidDataException("Invalid patient ID");
        }

        existing.setName(updatedPatient.getName());
        existing.setAge(updatedPatient.getAge());

        return existing;
    }

    /**
     * Delete Patient
     */
    public void deletePatient(int id) {
        if (!patientMap.containsKey(id)) {
            throw new InvalidDataException("Cannot delete. Patient not found: " + id);
        }

        patientMap.remove(id);
    }

    /**
     * Search by ID (Overloading)
     */
    public Patient searchPatient(int id) {
        return getPatientById(id);
    }

    /**
     * Search by Name (Overloading)
     */
    public List<Patient> searchPatient(String name) {
        return patientMap.values()
                .stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    /**
     * Search by Age (Overloading)
     */
    public List<Patient> searchPatient(int minAge, int maxAge) {
        return patientMap.values()
                .stream()
                .filter(p -> p.getAge() >= minAge && p.getAge() <= maxAge)
                .collect(Collectors.toList());
    }

    /**
     * Generic Search (Interface implementation)
     */
    @Override
    public List<Patient> search(String keyword) {

        if (!isValidKeyword(keyword)) {
            throw new InvalidDataException("Invalid search keyword");
        }

        return patientMap.values()
                .stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }


    /**
     * Clone Patient (Deep Copy )
     */
    public Patient clonePatient(int id) {
        try {
            Patient original = getPatientById(id);
            return (Patient) original.clone(); // ensure deep copy inside entity
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning failed", e);
        }
    }
}
