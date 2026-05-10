package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.strategy.BillingStrategy;

public class BillingService {

    private BillingStrategy strategy;

    public BillingService(BillingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(BillingStrategy strategy) {
        this.strategy = strategy;
    }

    public BillSummary generateBill(Bill bill) {
        return strategy.generate(bill);
    }
}
