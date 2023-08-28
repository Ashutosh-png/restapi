package com.workshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.DTO.UserRegistrationDto;
import com.workshop.Entity.LoginForm;
import com.workshop.Entity.User;
import com.workshop.Service.UserDetailServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin

public class UserRegistrationController {
	
	@Autowired
	UserDetailServiceImpl userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	



	
	
	
	  @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        String username = userRegistrationDto.getUsername();
        try {
            User user = userService.getByUsername(username);
            if (user != null) {
	                return ResponseEntity.badRequest().body("Username already exists");
	            }
	            userService.save(userRegistrationDto);
	            return ResponseEntity.ok("Registered Successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	        }
	    }
	
	

	  
	  
	  @GetMapping("/test")
	  public ResponseEntity<String> test(){
		  return ResponseEntity.ok("Home endpoint reached");
	  }
	  

}
