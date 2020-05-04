package com.damh.carpark.business.interfaces;

import com.damh.carpark.config.CarparkConfiguration;
import com.damh.carpark.data.Bill;
import com.damh.carpark.data.Slot;

public interface ICarparkManager {
	
	CarparkConfiguration configure(CarparkConfiguration carparkConfiguration);
	
	boolean setPricingPolicy(int policyID, double i, double j);

	Slot takeSlot(int carId);
	
	Bill freeSlot(int carId);
}
