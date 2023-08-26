package com.workshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.Repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;
	
	

}
