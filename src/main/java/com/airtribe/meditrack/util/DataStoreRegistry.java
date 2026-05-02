package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Appointment;

public class DataStoreRegistry {

    private static final DataStore<Doctor> doctorStore = new DataStore<>();
    private static final DataStore<Patient> patientStore = new DataStore<>();
    private static final DataStore<Appointment> appointmentStore = new DataStore<>();

    private DataStoreRegistry() {} // prevent instantiation

    public static DataStore<Doctor> getDoctorStore() {
        return doctorStore;
    }

    public static DataStore<Patient> getPatientStore() {
        return patientStore;
    }

    public static DataStore<Appointment> getAppointmentStore() {
        return appointmentStore;
    }
}