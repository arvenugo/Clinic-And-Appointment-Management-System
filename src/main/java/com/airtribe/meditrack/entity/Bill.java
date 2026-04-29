package com.airtribe.meditrack.entity;

import java.math.BigDecimal;

import com.airtribe.meditrack.interfaces.Payable;

public class Bill implements Payable{
	
	private int serialNumber;
	
	private String particulars;
	
	private BigDecimal amount;
	


	/**
	 * @return the serialNumber
	 */
	public int getSerialNumber() {
		return serialNumber;
	}



	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}



	/**
	 * @return the particulars
	 */
	public String getParticulars() {
		return particulars;
	}



	/**
	 * @param particulars the particulars to set
	 */
	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}



	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}



	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	@Override
	public void pay() {
		
	}
}
