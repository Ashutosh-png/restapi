package com.workshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.Entity.CabInfo;
import com.workshop.Repo.CabInfoRepo;

@Service
public class CabInfoService {

	@Autowired
	CabInfoRepo repo;
	
	
	public List<CabInfo> getAll(){
		return repo.findAll();
	}
}
