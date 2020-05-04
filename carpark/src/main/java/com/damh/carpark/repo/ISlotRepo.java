package com.damh.carpark.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.damh.carpark.data.Slot;

@Repository
public interface ISlotRepo extends JpaRepository<Slot, Integer> {

}
