package com.airtribe.meditrack.strategy;

import java.math.BigDecimal;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;

public class InsuranceBillingStrategy implements BillingStrategy {

    private static final BigDecimal TAX = new BigDecimal("0.05");
    private static final BigDecimal COPAY = new BigDecimal("0.20");

   

	@Override
	public BillSummary generate(Bill bill) {
		BigDecimal itemsTotal = bill.getItems().values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal subTotal = bill.getConsultationFee().add(itemsTotal);
        BigDecimal tax = subTotal.multiply(TAX);

        BigDecimal gross = subTotal.add(tax);
        BigDecimal patientPays = gross.multiply(COPAY);

        return new BillSummary(
                bill.getPatientId(),
                bill.getItems(),
                subTotal,
                tax,
                gross.subtract(patientPays), // covered amount
                patientPays
        );
		return null;
	}
}
