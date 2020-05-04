package com.damh.carpark.business.impl;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damh.carpark.business.interfaces.IChargingHandler;
import com.damh.carpark.data.Bill;
import com.damh.carpark.policy.IPricingPolicy;
import com.damh.carpark.repo.IBillRepo;

@Component
public class ChargingHandler implements IChargingHandler {

	int policyID;
	
	double hourRate, fixedRate;
	
	private IBillRepo billRepo;
	
	@Autowired
	public ChargingHandler(IBillRepo billRepo) {
		this.billRepo = billRepo;
	}
	
	@Override
	public boolean setPolicy(int policyID, double hourRate, double fixedRate) {
		if(policyID < 0) {
			return false;
		} else {
			this.policyID = policyID;
			this.hourRate = hourRate;
			this.fixedRate = fixedRate;
		}
		
		return true;
	}

	@Override
	public Bill calculateBill(Bill bill) {
		IPricingPolicy ammount;

		switch (policyID) {
		case 0:
			ammount = IPricingPolicy.hoursPolicy(hourRate, 
					hoursInCarpark(bill));
			break;
		case 1:
			ammount = IPricingPolicy.fixAndHoursPolicy(hourRate, 
					hoursInCarpark(bill), 
					fixedRate);
			break;

		default:
			ammount = IPricingPolicy.hoursPolicy(hourRate, 
					hoursInCarpark(bill));
			break;
		}
		bill.setFare(ammount.getPrice());
		return billRepo.save(bill);
	}
	
	private double hoursInCarpark(Bill bill) {
	    return bill.getEntryDate().until(bill.getExitDate(), ChronoUnit.MINUTES) / 60.;
	}

}
