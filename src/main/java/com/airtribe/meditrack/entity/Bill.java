package com.airtribe.meditrack.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Bill {
	
	private final int patientId;
    private final Map<String, BigDecimal> items = new LinkedHashMap<>();
    private double consultationFee =0;
    

    public Bill(int patientId) {
        this.patientId = Objects.requireNonNull(patientId);
    }

    public void addItem(String name, BigDecimal price) {
        items.put(name, price);
    }

    public void setConsultationFee(double fee) {
        this.consultationFee = fee;
    }

    

   
	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * @return the items
	 */
	public Map<String, BigDecimal> getItems() {
		return items;
	}

	/**
	 * @return the consultationFee
	 */
	public double getConsultationFee() {
		return consultationFee;
	}

	/**
	 * @param patientId
	 * @param consultationFee
	 */
	public Bill(int patientId, double consultationFee) {
		super();
		this.patientId = patientId;
		this.consultationFee = consultationFee;
	}
    
	
    
    
    
    
	
	
}
