package com.damh.carpark.business.impl;

import org.springframework.stereotype.Component;

import com.damh.carpark.business.interfaces.ISlotAssignationService;
import com.damh.carpark.data.SlotType;

@Component
public class SlotAssignationService implements ISlotAssignationService {

	@Override
	public SlotType assignSlot(int carId) {
		if(carId % 2 == 0) {
			return SlotType.STANDARD;
		} else if(carId % 5 == 0) {
			return SlotType.ELECTRIC_50;
		} else {
			return SlotType.ELECTRIC_20;
		}
	}
	
}
