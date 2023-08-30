package com.workshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.Entity.onewayTrip;
import com.workshop.Entity.roundTrip;
import com.workshop.Repo.OnewayTripRepo;
import com.workshop.Repo.RoundTripRepo;

@Service
public class TripService {
	
	@Autowired
	OnewayTripRepo repo;
	
	@Autowired
	RoundTripRepo roundrepo;
	
	public List<onewayTrip> getonewayTrip(String from , String to){
		return repo.findBySourceCityAndDestinationCity(to, from);
		
	}
	
	
	public List<roundTrip> getRoundTrip(String from , String to){
		return roundrepo.findBySourceCityAndDestinationCity(to, from);
		
	}
	
	

}
