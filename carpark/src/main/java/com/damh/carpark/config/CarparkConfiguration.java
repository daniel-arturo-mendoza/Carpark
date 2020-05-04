package com.damh.carpark.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "car park configuration")
public class CarparkConfiguration {

	private int policyID;
	
	@ApiModelProperty(name = "slots", required = true, value = "10", notes = "available slots")
	private int slots;
	
	@ApiModelProperty(name = "slotsE20", required = true, value = "10", notes = "available E20 slots")
	private int slotsE20;
	
	@ApiModelProperty(name = "slotsE50", required = true, value = "10", notes = "available E50 slots")
	private int slotsE50;
	
	public CarparkConfiguration(int policyID, int slots, int slotsE20, int slotsE50) {
		this.policyID = policyID;
		this.slots = slots;
		this.slotsE20 = slotsE20;
		this.slotsE50 = slotsE50;
	}
	
	public int getPolicyID() {
		return policyID;
	}

	public int getSlots() {
		return slots;
	}

	public int getSlotsE20() {
		return slotsE20;
	}

	public int getSlotsE50() {
		return slotsE50;
	}

}
