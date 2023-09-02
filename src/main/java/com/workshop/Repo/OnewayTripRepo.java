package com.workshop.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.Entity.onewayTrip;

@Repository
public interface OnewayTripRepo  extends JpaRepository<onewayTrip, Long>{
	
	List<Trip> findBySourceCityAndDestinationCity(String to, String from);


}
