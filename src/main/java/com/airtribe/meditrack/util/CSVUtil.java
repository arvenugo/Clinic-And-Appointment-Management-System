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
import com.airtribe.meditrack.enums.Gender;
import com.airtribe.meditrack.exception.InvalidDataException;

public class CSVUtil {
	
	static DataStore<Patient> patientData = DataStoreRegistry.getPatientStore();
	static DataStore<Appointment> appointmentData = DataStoreRegistry.getAppointmentStore();
	static DataStore<Doctor> doctorData = DataStoreRegistry.getDoctorStore();
	
	
	public static void loadDoctors(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    try {
				br.readLine();
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			        String name = values[0];
			        int age = Integer.parseInt(values[1]);
			        DoctorSpecialization doctorSpecialization = DoctorSpecialization.valueOf(values[2]);
			        double consultationFees = Double.parseDouble(values[3]);
			        int yoe = Integer.parseInt(values[4]);
			        String qualification = values[5];
			        Doctor doctor = new Doctor(name, age,doctorSpecialization,consultationFees, yoe, qualification);
			        doctorData.save(doctor);
			   
			    }
			} catch (IOException e) {
				System.err.println("Error occured in opening file");
				e.printStackTrace();
			 
		    
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			System.err.println("Error occured in opening file");
			e1.printStackTrace();
		}
	}
	
	public static void loadPatients(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    try {
				br.readLine();
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			        String name = values[0];
			        int age = Integer.parseInt(values[1]);
			        String gender = values[2];
			        Gender patientGender = Gender.valueOf(gender);
			        Patient patient = new Patient(name,age,patientGender);
			        patientData.save(patient);
			        
			    }
			} catch (IOException e) {
				System.err.println("Error occured in opening file");
				e.printStackTrace();
			 
		    
			}
		} catch (FileNotFoundException e1) {
			System.err.println("Error occured while reading file");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.err.println("Error occured in opening file");
			e1.printStackTrace();
		}
	}
		
		public static void loadAppointments(String filePath) {
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			    String line;
			    try {
					br.readLine();
				    while ((line = br.readLine()) != null) {
				        String[] values = line.split(",");
				        int doctorId = Integer.parseInt(values[0]);
				        Doctor doctor = doctorData.findAll().stream().filter(d -> d.getId() == doctorId).findFirst().orElse(null);
				        int patientId = Integer.parseInt(values[1]);
				        Patient patient = patientData.findAll().stream().filter(p -> p.getId() == patientId).findFirst().orElse(null);
				        LocalDateTime appointmentTime = LocalDateTime.parse(values[2]);
				        String complaints = values[3];
				        if(doctor == null || patient == null) {
				        	throw new InvalidDataException("Invalid doctorId or PatientId");
				        }
				        Appointment appointment = new Appointment(doctor,patient,appointmentTime,complaints);
				        appointmentData.save(appointment);
				    }
				} catch (IOException e) {
					System.err.println("Error occured in opening file");
					e.printStackTrace();
				 
			    
				}
			} catch (FileNotFoundException e1) {
				System.err.println("Error occured while reading file");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.err.println("Error occured in opening file");
				e1.printStackTrace();
			}
	}
}
