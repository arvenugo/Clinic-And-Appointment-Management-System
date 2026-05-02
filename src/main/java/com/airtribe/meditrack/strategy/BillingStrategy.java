package com.airtribe.meditrack.strategy;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;

public interface BillingStrategy {
    BillSummary generate(Bill bill);
}