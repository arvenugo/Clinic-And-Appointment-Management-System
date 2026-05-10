package com.airtribe.meditrack.enums;

public enum BillingType {
	
	STANDARD,
	INSURANCE,
	EMERGENCY;
	
	public static BillingType parseGender(String input) {
        try {
            return BillingType.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid gender: " + input);
        }
    }

}
