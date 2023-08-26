package com.workshop.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.Entity.CabInfo;
import com.workshop.Entity.User;
import com.workshop.Service.CabInfoService;
import com.workshop.Service.UserDetailServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin

public class MainController {
	
	
	  @Autowired
	    UserDetailServiceImpl userService;
	  
	  
	  @Autowired
	  CabInfoService CabService;

	    // ... other mappings ...

	  @GetMapping("/user/")
	    public String home() {
			  
		            return "user";
				
		        
		}
	  
	  @GetMapping("/user/cabpage")
	    public ResponseEntity<String> cab() {
	        return ResponseEntity.ok("cab page");
		}
	  
	  
	  @GetMapping("/user/cabinfo")
	  public List<CabInfo> getCab(){
		  return CabService.getAll();
	  }
	    
	    

}
