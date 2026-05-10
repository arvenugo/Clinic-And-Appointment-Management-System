package com.airtribe.meditrack.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.enums.DoctorSpecialization;

public class AIHelper {
	
	// Rule map: Symptom → Doctor Type
    private static final Map<String, DoctorSpecialization> RULES = new HashMap<>();

    static {
        // Fever / infection
        RULES.put("fever", DoctorSpecialization.GENERAL_PHYSICIAN);
        RULES.put("cold", DoctorSpecialization.GENERAL_PHYSICIAN);
        RULES.put("cough", DoctorSpecialization.GENERAL_PHYSICIAN);

        // Heart related
        RULES.put("chest pain", DoctorSpecialization.CARDIOLOGIST);
        RULES.put("high bp", DoctorSpecialization.CARDIOLOGIST);
        RULES.put("palpitations", DoctorSpecialization.CARDIOLOGIST);

        // Skin
        RULES.put("rash", DoctorSpecialization.DERMATOLOGIST);
        RULES.put("acne", DoctorSpecialization.DERMATOLOGIST);
        RULES.put("itching", DoctorSpecialization.DERMATOLOGIST);

        // Bones / joints
        RULES.put("joint pain", DoctorSpecialization.ORTHOPEDIC);
        RULES.put("fracture", DoctorSpecialization.ORTHOPEDIC);
        RULES.put("back pain", DoctorSpecialization.ORTHOPEDIC);

        // Children
        RULES.put("baby fever", DoctorSpecialization.PEDIATRICIAN);
        RULES.put("child cough", DoctorSpecialization.PEDIATRICIAN);

        // Women's health
        RULES.put("pregnancy", DoctorSpecialization.GYNECOLOGIST);
        RULES.put("period pain", DoctorSpecialization.GYNECOLOGIST);
    }

    /**
     * Suggest doctor based on symptoms
     */
    public List<Doctor> suggestDoctor(String symptom) {

    	DataStore<Doctor> doctors = new DataStore<Doctor>();
    	return	doctors.findAll().stream().
    		filter(d -> d.getSpecialization().equals(RULES.get(symptom))).collect(Collectors.toList());
       
    }



}
