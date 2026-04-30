package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.service.BillFactory;
import com.airtribe.meditrack.strategy.BillingStrategy;
import com.airtribe.meditrack.strategy.DiscountBilling;

public class Main {

    public static void main(String[] args) {
        //SINGle FACTORY IN FLOW
        BillingStrategy strategy = new DiscountBilling(); // chosen externally
//Bill bill = BillFactory.createBill(appointment, strategy);

    }
}
