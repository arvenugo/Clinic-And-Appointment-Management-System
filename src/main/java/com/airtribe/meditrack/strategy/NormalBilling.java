package com.airtribe.meditrack.service;

public class NormalBilling implements BillingStrategy {
	 @Override
	    public double calculate(double amount) {
	        return amount + (amount * 0.1); // 10% tax
	    }
}
