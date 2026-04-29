package com.airtribe.meditrack;

public class Main {
	
	public static void main(String[] args) {
		//SINGle FACTORY IN FLOW
BillingStrategy strategy = new DiscountBilling(); // chosen externally
Bill bill = BillFactory.createBill(appointment, strategy);
	
	}
}
