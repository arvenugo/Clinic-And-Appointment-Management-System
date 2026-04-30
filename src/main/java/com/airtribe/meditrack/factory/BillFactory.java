package com.airtribe.meditrack.factory;

import com.airtribe.meditrack.*;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.strategy.BillingStrategy;

public class BillFactory {
    private BillFactory() {
    }

    public static Bill createBill(Appointment appointment,
                                  BillingStrategy strategy) {

        double baseAmount = appointment.getDoctor().getConsultationFees();
        double total = strategy.calculate(baseAmount);

        return new Bill(appointment, baseAmount, total - baseAmount);
    }
}
