package com.damh.carpark.business.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damh.carpark.business.interfaces.ICarparkManager;
import com.damh.carpark.business.interfaces.IChargingHandler;
import com.damh.carpark.business.interfaces.ISlotAssignationService;
import com.damh.carpark.config.CarparkConfiguration;
import com.damh.carpark.data.Bill;
import com.damh.carpark.data.Slot;
import com.damh.carpark.data.SlotType;
import com.damh.carpark.repo.IBillRepo;
import com.damh.carpark.repo.ISlotRepo;

@Component
public class CarparkManager implements ICarparkManager {
	
	private ISlotRepo slotRepo;
	
	private IBillRepo billRepo;

	private IChargingHandler chargingHandler;
	
	private ISlotAssignationService slotAssignationService;
	
	@Autowired
	public CarparkManager(ISlotRepo slotRepo, IBillRepo billRepo, IChargingHandler chargingHandler, 
			ISlotAssignationService slotAssignationService) {
		this.slotRepo = slotRepo;
		this.billRepo = billRepo;
		this.chargingHandler = chargingHandler;
		this.slotAssignationService = slotAssignationService;
	}
	
	@Override
	public boolean setPricingPolicy(int policyID, double hourRate, double fixedRate) {
		return chargingHandler.setPolicy(policyID, hourRate, fixedRate);
	}

	@Override
	public CarparkConfiguration configure(CarparkConfiguration carparkConfiguration) {
		// ... is it already configured?
		
		int i = 1;
		for( ; i <= carparkConfiguration.getSlots(); i++) {
			slotRepo.save(new Slot(SlotType.STANDARD));
		}
		
		for(i = 1; i <= carparkConfiguration.getSlotsE20(); i++) {
			slotRepo.save(new Slot(SlotType.ELECTRIC_20));
		}
		
		for(i = 1; i <= carparkConfiguration.getSlotsE50(); i++) {
			slotRepo.save(new Slot(SlotType.ELECTRIC_50));
		}
		
		//this.pricingPolicyService.setPricingPolicy(carparkConfiguration.getPricingPolicy());
		
		// initialized = true;
		
		return carparkConfiguration;
	}

	@Override
	public Slot takeSlot(int carId) {
	    SlotType parkingSlotType = slotAssignationService.assignSlot(carId);
	    Slot slot = null;
	    for (Slot st : slotRepo.findAll()) {
			if(st.getType().equals(parkingSlotType) && st.isAvailable()) {
				slot = st;
				break;
			}
		}
	    if (slot != null) {
	      slot.setAvailable(false);
	      slotRepo.save(slot);
	      Bill bill = new Bill(carId, slot, LocalDateTime.now());
	      billRepo.save(bill);
	    }
	    
	    return slot;
	}

	@Override
	public Bill freeSlot(int carId) {
		Bill bill = null;
		for (Bill b : billRepo.findAll()) {
			if(b.getCarId() == carId && b.getExitDate() == null) {
				bill = b;
				break;
			}
		}
		if (bill != null) {
	      bill.setExitDate(LocalDateTime.now());
	      int slot_id = bill.getSlot().getID();
	      slotRepo.findById(slot_id).get().setAvailable(true);
	      chargingHandler.calculateBill(bill);
	    
		}

		return bill;
	}

}
