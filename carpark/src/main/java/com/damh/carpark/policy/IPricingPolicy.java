package com.damh.carpark.policy;

public interface IPricingPolicy {

	double getPrice();
  
    static IPricingPolicy hoursPolicy(double price, double hours) {
    	return () -> price * hours;
    }
  
    static IPricingPolicy fixAndHoursPolicy(double price, double hours, double fixAmmount) {
    	return () -> fixAmmount + (price * hours);
    }
}
