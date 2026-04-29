package com.airtribe.meditrack.service;
import com.airtribe.meditrack.*;
public class BillFactory  {
	private BillFactory() {}

    public static Bill createBill(Appointment appointment,
                                  BillingStrategy strategy) {

        double baseAmount = appointment.getDoctor().getConsultationFee();
        double total = strategy.calculate(baseAmount);

        return new Bill(appointment, baseAmount, total - baseAmount);
    }
}
