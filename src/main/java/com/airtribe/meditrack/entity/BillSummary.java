package com.airtribe.meditrack.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public final class BillSummary {

    private final String billId;
    private final String patientId;
    private final Map<String, BigDecimal> items;
    private final BigDecimal subTotal;
    private final BigDecimal tax;
    private final BigDecimal discount;
    private final BigDecimal finalAmount;
    private final LocalDateTime createdAt;

    public BillSummary(String patientId,
                       Map<String, BigDecimal> items,
                       BigDecimal subTotal,
                       BigDecimal tax,
                       BigDecimal discount,
                       BigDecimal finalAmount) {

        this.billId = UUID.randomUUID().toString();
        this.patientId = Objects.requireNonNull(patientId);
        this.items = Map.copyOf(items); // immutable copy
        this.subTotal = subTotal;
        this.tax = tax;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.createdAt = LocalDateTime.now();
    }

    public String getBillId() { return billId; }
    public String getPatientId() { return patientId; }
    public Map<String, BigDecimal> getItems() { return items; }
    public BigDecimal getSubTotal() { return subTotal; }
    public BigDecimal getTax() { return tax; }
    public BigDecimal getDiscount() { return discount; }
    public BigDecimal getFinalAmount() { return finalAmount; }

    @Override
    public String toString() {
        return "BillSummary{" +
                "billId='" + billId + '\'' +
                ", finalAmount=" + finalAmount +
                '}';
    }
}