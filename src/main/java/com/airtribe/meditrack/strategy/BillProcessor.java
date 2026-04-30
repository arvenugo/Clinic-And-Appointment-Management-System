package com.airtribe.meditrack.strategy;
import com.airtribe.meditrack.*;
public class BillProcessor {

    public double generateTotal(BillingStrategy strategy, double baseAmount) {
        return strategy.calculate(baseAmount);
    }
}
