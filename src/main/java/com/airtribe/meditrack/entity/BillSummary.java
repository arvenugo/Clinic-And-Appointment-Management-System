package com.airtribe.meditrack.entity;

public final class BillSummary {
	
	private final Patient patient;
	
	private final double totalCost;

	
	/**
	 * @param patient
	 * @param totalCost
	 */
	public BillSummary(Patient patient, double totalCost) {
		super();
		this.patient = patient;
		this.totalCost = totalCost;
	}
	

	
}
