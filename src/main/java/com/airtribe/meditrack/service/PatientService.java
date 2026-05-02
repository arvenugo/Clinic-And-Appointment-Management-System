package com.airtribe.meditrack.service;


import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.Gender;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.DataStoreRegistry;
import com.airtribe.meditrack.util.Validator;


import java.util.*;
import java.util.stream.Collectors;

public class PatientService implements Searchable<Patient> {

	static DataStore<Patient> patientData = DataStoreRegistry.getPatientStore();

    /**
     * Add Patient
     */
    public Patient createPatient(String name, int age,Gender gender) throws InvalidDataException {

        Validator.validateName(name);
        Validator.validateAge(age);
        Patient patient = new Patient(name, age, gender);
        patientData.save(patient);
        return patient;
    }

    /**
     * Get Patient by ID
     */
    public Patient getPatientById(int id) {
        Optional<Patient> patient = patientData.findById(id);

        if (patient == null) {
            throw new InvalidDataException("Patient not found with id: " + id);
        }

        return patient.get();
    }

    /**
     * Get All Patients
     */
    public List<Patient> getAllPatients() {
        return patientData.findAll();
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
        if (patientData.findById(id).isEmpty()) {
            throw new InvalidDataException("Cannot delete. Patient not found: " + id);
        }

        patientData.delete(id);
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
        return patientData.findAll()
                .stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    /**
     * Search by Age (Overloading)
     */
    public List<Patient> searchPatient(int minAge, int maxAge) {
        return patientData.findAll()
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

        return patientData.findAll()
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