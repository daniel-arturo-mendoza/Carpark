package com.damh.carpark.data;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Bill")
@Entity
@Table(name = "Bill")
public class Bill {
	
	@ApiModelProperty(name = "Bill ID", required = true, value = "0",
			notes = "Bill ID")
	private int id;
	
	@ApiModelProperty(name = "carId", required = true, value = "0",
		      notes = "car id")
	private int carId;
	
	@ApiModelProperty(name = "slot", required = true, value = "1", notes = "car slot")
	private Slot slot;
	
	@ApiModelProperty(name = "fare", required = true, value = "0.0",
		      notes = "fare per hour")
	private double fare;
	
	@ApiModelProperty(name = "entry date", required = true, value = "2020-04-02T18:52:30.001",
		      notes = "date and time of entry")
	private LocalDateTime entryDate;
	
	@ApiModelProperty(name = "exit date", required = false, value = "2020-04-02T18:52:30.001",
		      notes = "date and time of exit")
	private LocalDateTime exitDate;
	
	public Bill(int carId, Slot slot, LocalDateTime entryDate) {
	    this.carId = carId;
	    this.slot = slot;
	    this.entryDate = entryDate;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	@Column(name = "carId", nullable = false)
	public int getCarId() {
		return carId;
	}

	@ManyToOne
	@JoinColumn(name = "slots_id")
	public Slot getSlot() {
		return slot;
	}

	@Column(name = "price", nullable = false)
	public double getFare() {
		return fare;
	}

	@Column(name = "entry_date", nullable = false)
	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	@Column(name = "exit_date", nullable = false)
	public LocalDateTime getExitDate() {
		return exitDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	public void setExitDate(LocalDateTime exitDate) {
		this.exitDate = exitDate;
	}

}
