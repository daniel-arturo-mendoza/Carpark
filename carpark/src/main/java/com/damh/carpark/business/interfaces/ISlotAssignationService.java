package com.damh.carpark.business.interfaces;

import com.damh.carpark.data.SlotType;

public interface ISlotAssignationService {

	public SlotType assignSlot(int carId);
}
