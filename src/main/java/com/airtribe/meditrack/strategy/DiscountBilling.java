package com.airtribe.meditrack.service;

public class NormalBilling implements BillingStrategy {
	@Override
    public double calculate(double amount) {
        double discounted = amount * 0.9; // 10% discount
        return discounted + (discounted * 0.1);
    }
}
