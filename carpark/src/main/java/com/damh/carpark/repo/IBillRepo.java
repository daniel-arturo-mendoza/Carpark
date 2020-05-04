package com.damh.carpark.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.damh.carpark.data.Bill;

@Repository
public interface IBillRepo extends JpaRepository<Bill, Integer> {

}
