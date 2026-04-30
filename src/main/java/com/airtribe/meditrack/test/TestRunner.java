package com.airtribe.meditrack.test;


import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.DoctorSpecialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

import java.time.LocalDateTime;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {
        try {
            // 1. Initialize Services
            DoctorService doctorService = new DoctorService();
            PatientService patientService = new PatientService();
            AppointmentService appointmentService = new AppointmentService();

            System.out.println("*** Begin Tests****\n");

            // 2. Create Doctors
            Doctor doc1 = doctorService.createDoctor("Arya", 30, "Baptist", DoctorSpecialization.CARDIOLOGY, 500.0);
            Doctor doc2 = doctorService.createDoctor("Saroj", 30, "Fortis", DoctorSpecialization.NEUROLOGY, 1000.0);
            System.out.println("Registered Doctors: " + doc1.getName() + " and " + doc2.getName());

            // 3. Create Patients
            Patient pat1 = patientService.createPatient("Rukmini", 50);
            Patient pat2 = patientService.createPatient("Murali", 55);
            System.out.println("Registered Patients: " + pat1.getName() + " and " + pat2.getName());

            // 4. Set up Observers (Notifications)
            PatientNotification notificationSystem = new PatientNotification(pat1);
            appointmentService.registerObserver(notificationSystem);
            System.out.println("Patient registered for notification " + pat1.getName());


            System.out.println("\n***Appointment Tests***");

            // Using future date
            LocalDateTime apptTime = LocalDateTime.now().plusDays(2);

            Appointment appointment = appointmentService.createAppointment(pat1, doc1, apptTime, "Heart ache");
            System.out.println("Created Appointment ID: " + appointment.getApptId());

            // Reschedule
            appointmentService.rescheduleAppointment(appointment.getApptId(), apptTime.plusHours(2));
            System.out.println("Appointment rescheduled to: " + appointment.getApptTime());

            // 6. Search Functionality
            System.out.println("\n*** Search for docs ***");
            List<Doctor> cardiologists = doctorService.searchDoctor(DoctorSpecialization.CARDIOLOGY);
            System.out.println("Found " + cardiologists.size() + " Cardiology specialists.");

            List<Patient> searchedPatients = patientService.search("Rukmini");
            System.out.println("Search 'Rukmini' results: " + searchedPatients.get(0).getName());

            // 7. Statistics & Aggregation
            double avgFee = doctorService.getAverageConsultationFee();
            System.out.println("\nAverage Consultation Fee: $" + avgFee);

            // 8. Cancellation
            appointmentService.cancelAppointment(appointment.getApptId());
            System.out.println("Appointment status after cancellation: " + appointment.getApptStatus());


        } catch (Exception e) {
            System.err.println("Test Failed: " + e.getMessage());

        }
    }
}
