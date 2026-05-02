package com.airtribe.meditrack;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.DoctorSpecialization;
import com.airtribe.meditrack.enums.Gender;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.AIHelper;
import com.airtribe.meditrack.util.DateUtil;

public class Main {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        int choice;

        do {
            // Main Menu
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Manage Doctors");
            System.out.println("2. Manage Patient");
            System.out.println("3. Create/View/Cancel Appointment");
            System.out.println("4. Search Doctor/Patient");
            System.out.println("5. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                	manageDoctor(sc);
                    break;
                case 2:
                	managePatient(sc);
                    break;
                case 3:
                	managePatient(sc);
                	break;
                case 4:
                	manageAppointment(sc);
                	break;
                case 5:
                	searchDoctorPatient(sc);
                	break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    
    }
    
    private static void manageDoctor(Scanner sc) {
        int subChoice;
        DoctorService doctorService = new DoctorService();
        do {
            System.out.println("\n--- Manage Doctor ---");
            System.out.println("1. Add new Doctor");
            System.out.println("2. Update existing Doctor");
            System.out.println("3. Delete doctor");
            System.out.print("Enter choice: ");
            subChoice = sc.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("*****Enter Doctor Data****");
                    System.out.println("Enter Doctor Name");
                    String name = sc.nextLine();
                    System.out.println("Enter Doctor Age");
                    int age = sc.nextInt();
                    System.out.println("Enter Doctor consultation fees");
                    double consultationFees = sc.nextDouble();
                    System.out.println("Enter Doctor years of experince");
                    int yoe = sc.nextInt();
                    System.out.println("Enter Doctor specialization");
                    String specialization = sc.nextLine();
                    DoctorSpecialization doctorSpecialization = DoctorSpecialization.valueOf(specialization);
                    System.out.println("Enter Doctor qualification");
                    String qualification = sc.nextLine();
                    doctorService.createDoctor(name, age, doctorSpecialization, consultationFees, yoe,qualification);
                    break;
                case 2:
                	System.out.println("Enter Doctor Id to update");
                    int updateDoctor = sc.nextInt();
                    System.out.println("Enter Doctor consultation fees");
                    double consultationFeesUpdate = sc.nextDouble();
                    System.out.println("Enter Doctor years of experince");
                    int yoeUpdate = sc.nextInt();
                    System.out.println("Enter Doctor specialization");
                    String specializationUpdate = sc.nextLine();
                    DoctorSpecialization doctorSpecializationUpdate = DoctorSpecialization.valueOf(specializationUpdate);
                    Doctor doctorUpdate = new Doctor(updateDoctor,doctorSpecializationUpdate,consultationFeesUpdate,yoeUpdate);
                    doctorService.updateDoctor(updateDoctor,doctorUpdate);
                    break;
                case 3:
                    System.out.println("Enter doctor Id to delete");
                    int deleteDoctor = sc.nextInt();
                    doctorService.deleteDoctor(deleteDoctor);
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (subChoice != 3);
    }
    
    private static void managePatient(Scanner sc) {
        int subChoice;
        PatientService patientService = new PatientService();
        do {
            System.out.println("\n--- Manage Patient ---");
            System.out.println("1. Add new Pateint");
            System.out.println("2. Update existing Patient");
            System.out.println("3. Delete Patient");
            System.out.print("Enter choice: ");
            subChoice = sc.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("*****Enter Patient Data****");
                    System.out.println("Enter Patient Name");
                    String name = sc.nextLine();
                    System.out.println("Enter Patient Age");
                    int age = sc.nextInt();
                    System.out.println("Enter Patient Gender (M/F/O)");
                    String gender = sc.nextLine().trim().toUpperCase();
                    
                    patientService.createPatient(name, age, Gender.valueOf(gender));
                    break;
                case 2:
                    System.out.println("******Update Patient******");
                    System.out.println("Enter Patient Id to update");
                    int patientId = sc.nextInt();
                    Patient patient = patientService.getPatientById(patientId);
                    System.out.println("Enter Patient Name");
                    String updatedName = sc.nextLine();
                    System.out.println("Enter Patient Age");
                    int updatedAge = sc.nextInt();
                    Patient updatePatient = new Patient(patientId,updatedName,updatedAge);
                    patientService.updatePatient(patientId, updatePatient);
                    
                    break;
                case 3:
                    System.out.println("*****Delete Patient******");
                    System.out.println("Enter Patient Id to delete");
                    int patientIdToDelete = sc.nextInt();
                    patientService.deletePatient(patientIdToDelete);
                    break;
                
                default:
                    System.out.println("Invalid choice!");
            }

        } while (subChoice != 3);
    }
    
    private static void manageAppointment(Scanner sc) {
        int subChoice;
        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        AppointmentService appointmentService = new AppointmentService();
        do {
            System.out.println("\n--- Manage Appointment ---");
            System.out.println("1. Create new Appointment");
            System.out.println("2. View Appointment");
            System.out.println("3. Cancel Appointment");
            System.out.print("Enter choice: ");
            subChoice = sc.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("*****Create new appointment******");
                    System.out.println("Enter Patient Id to create appointment");
                    int patientId = sc.nextInt();
                    System.out.println("Enter Patient symptoms");
                    String symptom = sc.nextLine();
                    AIHelper aiHelper = new AIHelper();
                    List<Doctor> doctors = aiHelper.suggestDoctor(symptom);
                    System.out.println("Based on your symptoms ,following are the suggested doctors");
                    doctors.stream().forEach(d -> d.toString());
                    System.out.println("Enter doctor Id");
                    int doctorId = sc.nextInt();
                    System.out.print("Enter appointent date and time (yyyy-MM-dd HH:mm): ");
                    String appointmnetTime = sc.nextLine();
                    
                    Patient patient = patientService.getPatientById(patientId);
                    
                    Doctor doctor = doctorService.getDoctorById(doctorId);
                    
                    
                    appointmentService.createAppointment(patient, doctor, DateUtil.parseDate(appointmnetTime), symptom);
                    
                    break;
                case 2:
          
                    System.out.println("Are you a 1.Doctor or 2.Patient");
                    int id = sc.nextInt();
                    if(id ==1) {
                    	doctorService.getAllDoctors().stream()
                        .filter(d -> id == d.getDoctorId())
                        .findFirst()
                        .ifPresent(d ->
                            d.getListOfAppointments().stream()
                                .filter(a -> a.getApptTime().isAfter(LocalDateTime.now()))
                                .forEach(a -> System.out.println(a))
                        );
                    }else if (id == 2) {
                    	patientService.getAllPatients()
                    	.stream()
                        .filter(p -> id == p.getPatientId())
                        .findFirst()
                        .ifPresent(p ->
                            p.getAppointments()
                            .stream()
                                .filter(a -> a.getApptTime().isAfter(LocalDateTime.now()))
                                .forEach(a -> System.out.println(a))
                        );
                    }else {
                    	System.out.println("Wrong selection");
                    }
                    break;
                case 3:
                    System.out.println("******Cancel Appointment******");
                    System.out.println("Enter Appointment Id to cancel");
                    int cancelAppointmentId = sc.nextInt();
                    appointmentService.cancelAppointment(cancelAppointmentId);
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (subChoice != 3);
    }
    
    private static void searchDoctorPatient(Scanner sc) {
        int subChoice;
        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        do {
            System.out.println("\n--- Search Doctor/Patient ---");
            System.out.println("1. Search doctor by id,name");
            System.out.println("1. Search doctor by Specialization");
            System.out.println("2. Search Patient by id,name");
            System.out.print("Enter choice: ");
            subChoice = sc.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("Search doctor");
                    System.out.println("Enter doctor id or name");
                    String doctorSearchString = sc.nextLine().trim();
                    try{
                    	int doctorInt = Integer.parseInt(doctorSearchString);
                    	Doctor doctor = doctorService.searchDoctor(doctorInt);
                    	doctor.toString();
                    }catch(NumberFormatException e) {
                    	List<Doctor> searcheddoctor = doctorService.searchDoctor(doctorSearchString);
                    	searcheddoctor.stream().forEach(System.out::println);
                    }
                    break;
                case 2:
                    System.out.println("Search doctor by Specialization");
                    System.out.println("Enter Specialization");
                    String specialization = sc.nextLine();
                    DoctorSpecialization doctorSpecialization = DoctorSpecialization.valueOf(specialization);
                    List<Doctor> searchedBySpecialization = doctorService.searchDoctor(doctorSpecialization);
                    searchedBySpecialization.stream().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Search Patient");
                    System.out.println("Enter patient id or name");
                    String patientSearchString = sc.nextLine().trim();
                    try{
                    	int patientInt = Integer.parseInt(patientSearchString);
                    	Patient patient = patientService.searchPatient(patientInt);
                    	patient.toString();
                    }catch(NumberFormatException e) {
                    	List<Patient> searchedPatient= patientService.searchPatient(patientSearchString);
                    	searchedPatient.stream().forEach(System.out::println);
                    }
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (subChoice != 3);
    }
    
    private static void generateBill(Scanner sc) {
        int subChoice;
        System.out.println("Enter appointment id to generate bill");
        System.out.println("Patient type: In-patient , Out-patient");
        
        
       
        
    }
}
