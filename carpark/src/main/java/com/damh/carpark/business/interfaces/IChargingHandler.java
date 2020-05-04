package com.damh.carpark.business.interfaces;

import com.damh.carpark.data.Bill;

public interface IChargingHandler {

	public boolean setPolicy(int policyID, double i, double j);
	
	Bill calculateBill(Bill bill);
}
