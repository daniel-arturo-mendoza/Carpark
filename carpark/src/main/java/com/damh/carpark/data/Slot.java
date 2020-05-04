package com.damh.carpark.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "wololo")
@Entity
@Table(name = "Slot")
public class Slot {

	@ApiModelProperty(name = "id", required = true, value = "0", notes = "Slot ID")
	private int id;
	
	@ApiModelProperty(name = "type", required = true,
			value = "standard, electric 20, electric 50", notes = "Slot type")
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private SlotType type;
	
	@ApiModelProperty(
		      name = "isAvailable",
		      required = true,
		      value = "true, false",
		      notes = "default value is true")
	private boolean isAvailable = true;
	
	public Slot(SlotType type) {
		this.type = type;
	}
	
	public SlotType getType() {
		return type;
	}

	public void setType(SlotType type) {
		this.type = type;
	}

	@Column(name = "availability")
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
