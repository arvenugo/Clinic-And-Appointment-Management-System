package com.airtribe.meditrack;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.BillingType;
import com.airtribe.meditrack.enums.DoctorSpecialization;
import com.airtribe.meditrack.enums.Gender;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.BillingService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.strategy.BillingStrategy;
import com.airtribe.meditrack.strategy.EmergencyBillingStrategy;
import com.airtribe.meditrack.strategy.InsuranceBillingStrategy;
import com.airtribe.meditrack.strategy.StandardBillingStrategy;
import com.airtribe.meditrack.util.AIHelper;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.DateUtil;

public class Main {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        int choice;
        
        String filePath = null;

        if (args != null) {
            for (String arg : args) {
                if (arg != null && arg.startsWith("--loadData=")) {
                    String[] parts = arg.split("=", 2);
                    if (parts.length == 2 && !parts[1].isEmpty()) {
                        filePath = parts[1];
                    } else {
                        throw new IllegalArgumentException("Invalid --loadData argument");
                    }
                }
            }
        }
        if(null!= filePath &&  !filePath.isBlank() && !filePath.isEmpty()) {
        if(filePath.contains("appointment")) {
        	CSVUtil.loadAppointments(filePath);
        }else if(filePath.contains("doctors")) {
        	CSVUtil.loadDoctors(filePath);
        }else if(filePath.contains("patient")) {
        	CSVUtil.loadPatients(filePath);
        }
        }

        do {
            // Main Menu
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Manage Doctors");
            System.out.println("2. Manage Patient");
            System.out.println("3. Create/View/Cancel Appointment");
            System.out.println("4. Search Doctor/Patient");
            System.out.println("5. Generate Bill");
            System.out.println("6. Exit");
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
                	manageAppointment(sc);
                	break;
                case 4:
                	searchDoctorPatient(sc);
                	break;
                case 5:
                	generateBill(sc);
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
        while(true) {
            System.out.println("\n--- Manage Doctor ---");
            System.out.println("1. Add new Doctor");
            System.out.println("2. Update existing Doctor");
            System.out.println("3. Delete doctor");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            subChoice = sc.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("*****Enter Doctor Data****");
                    System.out.println("Enter Doctor Name");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Enter Doctor Age");
                    int age = sc.nextInt();
                    System.out.println("Enter Doctor consultation fees");
                    double consultationFees = sc.nextDouble();
                    System.out.println("Enter Doctor years of experince");
                    int yoe = sc.nextInt();
                    System.out.println("Enter Doctor specialization");
                    sc.nextLine();
                    String specialization = sc.nextLine();
                    System.out.println("Enter Doctor qualification");
                    String qualification = sc.nextLine();
                    doctorService.createDoctor(name, age, DoctorSpecialization.parseEnum(specialization), consultationFees, yoe,qualification);
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
                    
                    Doctor doctorUpdate = new Doctor(updateDoctor,DoctorSpecialization.parseEnum(specializationUpdate),consultationFeesUpdate,yoeUpdate);
                    doctorService.updateDoctor(updateDoctor,doctorUpdate);
                    break;
                case 3:
                    System.out.println("Enter doctor Id to delete");
                    int deleteDoctor = sc.nextInt();
                    doctorService.deleteDoctor(deleteDoctor);
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
               
                default:
                    System.out.println("Invalid choice!");
            }

        } 
    }
    
    private static void managePatient(Scanner sc) {
        int subChoice;
        PatientService patientService = new PatientService();
        while(true) {
            System.out.println("\n--- Manage Patient ---");
            System.out.println("1. Add new Pateint");
            System.out.println("2. Update existing Patient");
            System.out.println("3. Delete Patient");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            subChoice = sc.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("*****Enter Patient Data****");
                    System.out.println("Enter Patient Name");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Enter Patient Age");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Patient Gender (M/F/O)");
                    String gender = sc.nextLine().trim().toUpperCase();
                    
                    patientService.createPatient(name, age, Gender.parseGender(gender));
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
                case 0:
                	System.out.println("Returning to main menu...");
                	return;
                default:
                    System.out.println("Invalid choice!");
            }

        } 
    }
    
    private static void manageAppointment(Scanner sc) {
        int subChoice;
        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        AppointmentService appointmentService = new AppointmentService();
        while(true) {
            System.out.println("\n--- Manage Appointment ---");
            System.out.println("1. Create new Appointment");
            System.out.println("2. View Appointment");
            System.out.println("3. Cancel Appointment");
            System.out.println("0. Back to Main Menu");
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
                        .filter(d -> id == d.getId())
                        .findFirst()
                        .ifPresent(d ->
                            d.getListOfAppointments().stream()
                                .filter(a -> a.getApptTime().isAfter(LocalDateTime.now()))
                                .forEach(a -> System.out.println(a))
                        );
                    }else if (id == 2) {
                    	patientService.getAllPatients()
                    	.stream()
                        .filter(p -> id == p.getId())
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
                case 0:
                	System.out.println("Returning to main menu...");
                	return;
                	
                default:
                    System.out.println("Invalid choice!");
            }

        }
    }
    
    private static void searchDoctorPatient(Scanner sc) {
        int subChoice;
        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        while(true) {
            System.out.println("\n--- Search Doctor/Patient ---");
            System.out.println("1. Search doctor by id,name");
            System.out.println("2. Search doctor by Specialization");
            System.out.println("3. Search Patient by id,name");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            subChoice = sc.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("Search doctor");
                    System.out.println("Enter doctor id or name");
                    sc.nextLine();
                    String doctorSearchString = sc.nextLine().trim();
                    try{
                    	int doctorInt = Integer.parseInt(doctorSearchString);
                    	Doctor doctor = doctorService.searchDoctor(doctorInt);
                    	System.out.println(doctor);
                    	}catch(NumberFormatException e) {
                    	List<Doctor> searcheddoctor = doctorService.searchDoctor(doctorSearchString);
                    	searcheddoctor.stream().forEach(System.out::println);
                    }
                    break;
                case 2:
                    System.out.println("Search doctor by Specialization");
                    System.out.println("Enter Specialization");
                    sc.nextLine();
                    String specialization = sc.nextLine();
                    DoctorSpecialization doctorSpecialization = DoctorSpecialization.valueOf(specialization);
                    List<Doctor> searchedBySpecialization = doctorService.searchDoctor(doctorSpecialization);
                    searchedBySpecialization.stream().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Search Patient");
                    System.out.println("Enter patient id or name");
                    sc.nextLine();
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
                case 0:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }

        } 
    }
    
    private static void generateBill(Scanner sc) {
       
        AppointmentService appointmentService = new AppointmentService();
        System.out.println("Enter appointment id to generate bill");
        int appointmentId = sc.nextInt();
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        System.out.println("Enter billing type (Standard, Insurance, Emergency)");
        String billingType = sc.nextLine();
        System.out.println("Enter Medicine charges");
        BigDecimal medicineChargesBD = sc.nextBigDecimal();
        System.out.println("Enter Room rental charges");
        BigDecimal roomRentalCharges = sc.nextBigDecimal();
        
        BillingType billingTypeEnum = BillingType.valueOf(billingType);
        BillingStrategy billingStrategy= null;
        if(billingTypeEnum.equals(BillingType.STANDARD)) {
        	billingStrategy = new StandardBillingStrategy();
        }else if(billingTypeEnum.equals(BillingType.INSURANCE)) {
        	billingStrategy = new InsuranceBillingStrategy();
        }else if(billingTypeEnum.equals(BillingType.EMERGENCY)) {
        	billingStrategy = new EmergencyBillingStrategy();
        }
        Bill bill = new Bill(appointment.getPatient().getId(), appointment.getDoctor().getConsultationFees());
        bill.addItem("Medicine", medicineChargesBD);
        bill.addItem("Room Rental Charges", roomRentalCharges);
        
        BillingService billingService = new BillingService(billingStrategy);
        billingService.generateBill(bill);
        
        return;
        
       
        
    }
}
