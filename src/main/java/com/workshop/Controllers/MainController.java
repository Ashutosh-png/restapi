package com.workshop.Controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.Entity.CabInfo;
import com.workshop.Entity.User;
import com.workshop.Entity.onewayTrip;
import com.workshop.Entity.roundTrip;
import com.workshop.Repo.Trip;
import com.workshop.Service.CabInfoService;
import com.workshop.Service.TripService;
import com.workshop.Service.UserDetailServiceImpl;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3002"}) // Specify the allowed origin

@RestController


public class MainController {
	
	
	  @Autowired
	    UserDetailServiceImpl userService;
	  
	  
	  @Autowired
	  TripService tripSer;
	  
	  
	  @Autowired
	  CabInfoService CabService;

	    // ... other mappings ...
	  
	  
//	  @PostMapping("/user/oneway")
//	  public List<onewayTrip> getoneway(@RequestBody Map<String, String> requestBody){
//		  String to = requestBody.get("to");
//		  String from = requestBody.get("from");
//		  
//	        String city1 = userService.getLongNameByCity(to,apiKey);
//	        String[] parts = city1.split(" ");
//	        String cityName = parts[0];
//	        String city2 = userService.getLongNameByCity(from,apiKey);
//	        String[] parts1 = city2.split(" ");
//	        String cityName1 = parts1[0];
//	        System.out.println(city1);
//	        System.out.println(city2);
//	        System.out.println(cityName);
//	        System.out.println(cityName1);
//
//
//
//		  
//		  return tripSer.getonewayTrip(cityName, cityName1);
//		  
//
//		  
//	  }
//
//	  
//	  @PostMapping("/user/roundTrip")
//	  public List<roundTrip> getroundTrip(@RequestBody Map<String, String> requestBody){
//		  String to = requestBody.get("to");
//		  String from = requestBody.get("from");
//		  
//	        String city1 = userService.getLongNameByCity(to,apiKey);
//	        String[] parts = city1.split(" ");
//	        String cityName = parts[0];
//	        String city2 = userService.getLongNameByCity(from,apiKey);
//	        String[] parts1 = city2.split(" ");
//	        String cityName1 = parts1[0];
//	        System.out.println(city1);
//	        System.out.println(city2);
//	        System.out.println(cityName);
//	        System.out.println(cityName1);
//
//
//
//		  
//		  return tripSer.getRoundTrip(cityName, cityName1);
//		  
//
//		  
//	  }

	  
	  
	  @PostMapping("/user/getPrice")
	  public List<Trip> getPrice(@RequestBody Map<String, String> requestBody) {
	      String to = requestBody.get("to");
	      String from = requestBody.get("from");
	      String tripType = requestBody.get("tripType");

	      String city1 = userService.getLongNameByCity(to, apiKey);
	      String[] parts = city1.split(" ");
	      String cityName = parts[0];
	      String city2 = userService.getLongNameByCity(from, apiKey);
	      String[] parts1 = city2.split(" ");
	      String cityName1 = parts1[0];
	      
	      System.out.println(city1);
	      System.out.println(city2);
	      System.out.println(cityName);
	      System.out.println(cityName1);

	      if ("oneway".equals(tripType)) {
	          return tripSer.getonewayTrip(cityName, cityName1);
	      } else if ("roundTrip".equals(tripType)) {
	          return tripSer.getRoundTrip(cityName, cityName1);
	      } else {
	          // Handle other cases or return an empty list if needed
	          return new ArrayList<>();
	      }
	  }
	  
	  
	  @PostMapping("/user/getRoundDistance")
	  public int getRoundDistance(@RequestBody Map<String, String> requestBody) {
		  String startdate = requestBody.get("date");
		  String enddate = requestBody.get("dateend");
		  String time = requestBody.get("time");
		  String endtime = requestBody.get("timeend");
		  String distance = requestBody.get("distance");
		  System.out.println(startdate+" "+enddate+" "+time+" "+distance);
		  LocalDate localDate1 = LocalDate.parse(startdate, DateTimeFormatter.ISO_DATE);
		    LocalDate localDate2 = LocalDate.parse(enddate, DateTimeFormatter.ISO_DATE);

		    // Use ISO_TIME pattern for parsing time strings
		    LocalTime localTime1 = LocalTime.parse(time, DateTimeFormatter.ISO_TIME);
		    LocalTime localTime2 = LocalTime.parse(endtime, DateTimeFormatter.ISO_TIME);

		    return tripSer.getRoundDistance(localDate1, localTime1, localDate2, localTime2, distance);
		  
	  }

	  
	  
	  
	  
	  
	  
	  
	  @GetMapping("/user")
	    public  ResponseEntity<String> home() {
			  
	        return ResponseEntity.ok("user page");
				
		        
		}
	  
	  @RequestMapping("/admin")
	    public ResponseEntity<String> cab() {
	        return ResponseEntity.ok("cab page");
		}
	  
	  
	  @RequestMapping("/cabinfo")
	  public List<CabInfo> getCab(){
		  return CabService.getAll();
	  }
	  
	  
	  
	  
	  private final String apiKey = "AIzaSyCelDo4I5cPQ72TfCTQW-arhPZ7ALNcp8w"; // Replace with your Google API key

//	  @PostMapping("/user/getPincode")
//	  public ResponseEntity<String> getPincode(@RequestBody Map<String, String> requestBody) {
//	      String city = requestBody.get("city");
//	      if (city == null || city.isEmpty()) {
//	          return ResponseEntity.badRequest().body("City parameter is missing or empty.");
//	      }
//	      
//	      String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city + "&key=" + apiKey;
//	      
//	      RestTemplate restTemplate = new RestTemplate();
//	      ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
//	      
//	      // Assuming you're using a JSON parsing library like Jackson
//	      ObjectMapper objectMapper = new ObjectMapper();
//	      try {
//	          JsonNode root = objectMapper.readTree(response.getBody());
//	          JsonNode locationNode = root.path("results").get(0).path("geometry").path("location");
//	          double latitude = locationNode.path("lat").asDouble();
//	          double longitude = locationNode.path("lng").asDouble();
//	          
//	          // Construct the new URL for reverse geocoding using latlng
//	          String reverseGeocodeUrl = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&key=" + apiKey;
//	          ResponseEntity<String> reverseResponse = restTemplate.getForEntity(reverseGeocodeUrl, String.class);
//	          
//	          JsonNode reverseRoot = objectMapper.readTree(reverseResponse.getBody());
//	          String pincode = null;
//	          
//	          for (JsonNode result : reverseRoot.path("results")) {
//	              for (JsonNode component : result.path("address_components")) {
//	                  for (JsonNode type : component.path("types")) {
//	                      if (type.asText().equals("postal_code")) {
//	                          pincode = component.path("long_name").asText();
//	                          break;
//	                      }
//	                  }
//	                  if (pincode != null) {
//	                      break;
//	                  }
//	              }
//	              if (pincode != null) {
//	                  break;
//	              }
//	          }
//	          
//	          if (pincode != null) {
//	              return ResponseEntity.ok("Pincode: " + pincode);
//	          } else {
//	              return ResponseEntity.ok("Pincode not found for the provided city.");
//	          }
//	      } catch (IOException e) {
//	          e.printStackTrace();
//	          return ResponseEntity.badRequest().body("Error extracting coordinates.");
//	      }
//	  }
	  
	  
//	  @PostMapping("/user/getLongName")
//	  public ResponseEntity<String> getLongName(@RequestBody Map<String, String> requestBody) {
//	      String city = requestBody.get("city");
//	      if (city == null) {
//	          return ResponseEntity.badRequest().body("City not provided in the request body.");
//	      }
//
//	      String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city + "&key=" + apiKey;
//
//	      RestTemplate restTemplate = new RestTemplate();
//	      ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
//
//	      ObjectMapper objectMapper = new ObjectMapper();
//	      try {
//	          JsonNode root = objectMapper.readTree(response.getBody());
//	          JsonNode result = root.path("results").get(0);
//
//	          // Find the administrative area level 3 (Jalgaon) component
//	          JsonNode adminAreaLevel3Component = null;
//	          for (JsonNode component : result.path("address_components")) {
//	              JsonNode types = component.path("types");
//	              if (types.isArray() && types.size() > 0 && types.get(0).asText().equals("administrative_area_level_3")) {
//	                  adminAreaLevel3Component = component;
//	                  break;
//	              }
//	          }
//
//	          if (adminAreaLevel3Component != null) {
//	              String longName = adminAreaLevel3Component.path("long_name").asText();
//	              return ResponseEntity.ok("Long Name: " + longName);
//	          } else {
//	              return ResponseEntity.badRequest().body("Administrative Area Level 3 not found.");
//	          }
//	      } catch (IOException e) {
//	          e.printStackTrace();
//	          return ResponseEntity.badRequest().body("Error retrieving long name.");
//	      }
//	  }
	  
	  @PostMapping("/user/getLongName")
	    public ResponseEntity<String> getLongName(@RequestBody Map<String, String> requestBody) {
	        String city = requestBody.get("city");
	        if (city == null) {
	            return ResponseEntity.badRequest().body("City not provided in the request body.");
	        }

	        String longName = userService.getLongNameByCity(city,apiKey);
	        return ResponseEntity.ok(longName);
	    }



}
