package com.airtribe.meditrack.strategy;

import java.math.BigDecimal;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;

public class EmergencyBillingStrategy implements BillingStrategy {

    private static final BigDecimal TAX = new BigDecimal("0.05");
    private static final BigDecimal SURCHARGE = new BigDecimal("0.10");

    @Override
    public BillSummary generate(Bill bill) {

        BigDecimal itemsTotal = bill.getItems().values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal subTotal = bill.getConsultationFee().add(itemsTotal);
        BigDecimal surcharge = subTotal.multiply(SURCHARGE);
        BigDecimal tax = (subTotal.add(surcharge)).multiply(TAX);

        BigDecimal finalAmount = subTotal.add(surcharge).add(tax);

        return new BillSummary(
                bill.getPatientId(),
                bill.getItems(),
                subTotal,
                tax,
                BigDecimal.ZERO,
                finalAmount
        );
    }
}
