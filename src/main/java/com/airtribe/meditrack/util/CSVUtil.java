package com.airtribe.meditrack.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.DoctorSpecialization;
import com.airtribe.meditrack.exception.InvalidDataException;

public class CSVUtil {
	
	static DataStore<Patient> patientData = new DataStore<Patient>();
	static DataStore<Doctor> doctorData = new DataStore<Doctor>();
	
	public static void loadDoctors() {
		try (BufferedReader br = new BufferedReader(new FileReader("doctors.csv"))) {
		    String line;
		    try {
				br.readLine();
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			        String name = values[0];
			        int age = Integer.parseInt(values[1]);
			        String hospitalName = values[2];
			        DoctorSpecialization doctorSpecialization = DoctorSpecialization.valueOf(values[3]);
			        double consultationFees = Double.parseDouble(values[3]);
			        Doctor doctor = new Doctor(name, age,hospitalName,doctorSpecialization,consultationFees);
			        doctorData.add(doctor);
			   
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 
		    
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void loadPatients() {
		try (BufferedReader br = new BufferedReader(new FileReader("patient.csv"))) {
		    String line;
		    try {
				br.readLine();
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			        String name = values[0];
			        int age = Integer.parseInt(values[1]);
			        Patient patient = new Patient(name,age);
			        patientData.add(patient);
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 
		    
			}
		} catch (FileNotFoundException e1) {
			System.err.println("Error occured while reading file");
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
		public static void loadAppointments() {
			try (BufferedReader br = new BufferedReader(new FileReader("appointment.csv"))) {
			    String line;
			    try {
					br.readLine();
				    while ((line = br.readLine()) != null) {
				        String[] values = line.split(",");
				        int doctorId = Integer.parseInt(values[0]);
				        Doctor doctor = doctorData.getAll().stream().filter(d -> d.getDoctorId() == doctorId).findFirst().orElse(null);
				        int patientId = Integer.parseInt(values[1]);
				        Patient patient = patientData.getAll().stream().filter(p -> p.getPatientId() == patientId).findFirst().orElse(null);
				        LocalDateTime appointmentTime = LocalDateTime.parse(values[2]);
				        String complaints = values[3];
				        if(doctor == null || patient == null) {
				        	throw new InvalidDataException("Invalid doctorId or PatientId");
				        }
				        Appointment appointment = new Appointment(doctor,patient,appointmentTime,complaints);
				    }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 
			    
				}
			} catch (FileNotFoundException e1) {
				System.err.println("Error occured while reading file");
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}
