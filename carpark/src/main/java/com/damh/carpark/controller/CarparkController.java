package com.damh.carpark.controller;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.damh.carpark.business.interfaces.ICarparkManager;
import com.damh.carpark.config.CarparkConfiguration;
import com.damh.carpark.data.Bill;
import com.damh.carpark.data.Slot;

import io.swagger.annotations.ApiOperation;


@RestController("/api/v1")
public class CarparkController {

	private ICarparkManager carparkManager;
	
	@Autowired
	public CarparkController(ICarparkManager carparkManager) {
		this.carparkManager = carparkManager;
	}
	
	@PostMapping("/configure")
	public ResponseEntity<CarparkConfiguration> configureCarPark(@RequestBody CarparkConfiguration carparkConfiguration) throws Exception {
		CarparkConfiguration configuration = carparkManager.configure(carparkConfiguration);
		if(configuration == null) {
			throw new RuntimeErrorException(new Error(), "");
		}
		return ResponseEntity.ok(configuration);
	}
	
	@PutMapping("/pricingpolicy")
	public ResponseEntity<Integer> setPricingPolicy(@RequestBody int policyID, @RequestBody double hourRate, @RequestBody double fixedRate) throws Exception {
		if(carparkManager.setPricingPolicy(policyID, hourRate, fixedRate)) {
			return ResponseEntity.ok(policyID);
		} else {
			throw new RuntimeErrorException(new Error(), "");
		}
	}
	
	 @ApiOperation(value = "car enters to the carpark", response = Slot.class)
//	 @ApiResponses(value = { @ApiResponse(code = 200, message = "OK: Parking slot retrieved"),
//	          				 @ApiResponse(code = 404, message = "Not found: No available parking slot")})
	 @GetMapping("/enter/{carId}")
	 public ResponseEntity<Slot> carEnter(@PathVariable("carId") int carId) throws Exception {
		 Slot parkingSlot = carparkManager.takeSlot(carId);
		 return ResponseEntity.ok(parkingSlot);
	 }

	 @ApiOperation(value = "car exits the carpark", response = Bill.class)
//	 @ApiResponses(value = { @ApiResponse(code = 200, message = "OK: Parking bill retrieved"),
//	          				 @ApiResponse(code = 404, message = "Not found: Parking bill not found")})
	 @GetMapping("/exit/{carId}")
	 public ResponseEntity<Bill> leaveParking(@PathVariable("carId") int carId) throws Exception {
		 Bill parkingBill = carparkManager.freeSlot(carId);
		 return ResponseEntity.ok(parkingBill);
	 }
	
}
