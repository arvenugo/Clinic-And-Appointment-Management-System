package com.airtribe.meditrack.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.airtribe.meditrack.interfaces.Payable;

public class Bill {
	
	private final String patientId;
    private final Map<String, BigDecimal> items = new LinkedHashMap<>();
    private BigDecimal consultationFee = BigDecimal.ZERO;
    private boolean insured;

    public Bill(String patientId) {
        this.patientId = Objects.requireNonNull(patientId);
    }

    public void addItem(String name, BigDecimal price) {
        items.put(name, price);
    }

    public void setConsultationFee(BigDecimal fee) {
        this.consultationFee = fee;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public String getPatientId() { return patientId; }
    public Map<String, BigDecimal> getItems() { return items; }
    public BigDecimal getConsultationFee() { return consultationFee; }
    public boolean isInsured() { return insured; }ß
	
	
}
