package com.workshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.Entity.Booking;
import com.workshop.Service.BookingService;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin

public class BookingController {
	@Autowired
	BookingService ser;
	
	
	@PostMapping("/book")
	public ResponseEntity<String> book(@RequestBody Booking booking){
		System.out.println(booking);
		String bookid = ser.getLastUsedBookingId();
		if(bookid==null) {
			bookid = "AIM0";
		}else {
			 String numericPart = bookid.substring(3);
			    
			    try {
			        // Parse the numeric part to an integer and increment it
			        int numericValue = Integer.parseInt(numericPart);
			        numericValue++;
			        
			        // Create the new bookid with the incremented numeric part
			        bookid = "AIM" + numericValue;
			    } catch (NumberFormatException e) {
			        // Handle the case where the numeric part is not a valid integer
			        System.out.println("Invalid numeric part in bookid: " + numericPart);
			    }
			
		}
       
		booking.setBookingId(bookid);
		if(booking.getDistance()=="0") {
			System.out.println("distance not found");
			   return ResponseEntity.ok("not Successful");
		}
		ser.saveBooking(booking);
		System.out.println("booked ");
   return ResponseEntity.ok("Booked Successfully");

	}

}
