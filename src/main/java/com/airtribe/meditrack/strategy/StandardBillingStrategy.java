package com.airtribe.meditrack.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;

public class StandardBillingStrategy implements BillingStrategy {

    private static final BigDecimal TAX = new BigDecimal("0.05");
    private static final BigDecimal DISCOUNT = new BigDecimal("0.02");

    @Override
    public BillSummary generate(Bill bill) {

        BigDecimal itemsTotal = bill.getItems().values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal subTotal = BigDecimal.valueOf(bill.getConsultationFee())
                .add(itemsTotal);

        BigDecimal tax = subTotal.multiply(TAX);
        BigDecimal discount = subTotal.multiply(DISCOUNT);

        BigDecimal finalAmount = subTotal.add(tax).subtract(discount)
                .setScale(2, RoundingMode.HALF_UP);

        return new BillSummary(
                bill.getPatientId(),
                bill.getItems(),
                subTotal,
                tax,
                discount,
                finalAmount
        );
    }
}