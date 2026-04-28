package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Payable;

public class Bill implements Payable{
	
	private int billId;
	
	private BillSummary billSummary;
	
	

	@Override
	public void pay() {
		
	}
}
