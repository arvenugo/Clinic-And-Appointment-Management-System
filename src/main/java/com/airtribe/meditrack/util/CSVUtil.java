package com.airtribe.meditrack.util;

import java.io.BufferedReader;
import java.io.FileReader;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.AppointmentStatus;

public class CSVUtil {
	
	public static void loadPatients() {
		try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
		    String line;
		    br.readLine(); // Skip header
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        String name = values[0];
		        int age = Integer.parseInt(values[1]);
		        AppointmentStatus appointmentStatus = AppointmentStatus.valueOf(values[2]);
		        Patient patient = new Patient(name, age,appointmentStatus);
		        userList.add(user);
		    }
		}
	}
}
