package com.workshop.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.DTO.UserRegistrationDto;
import com.workshop.Entity.User;
import com.workshop.Entity.User.Role;
import com.workshop.Repo.UserRepo;
import com.workshop.Repo.UserServiceRepo;


@Service
public class UserDetailServiceImpl implements UserServiceRepo{
	
	@Autowired
	UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = repo.findByUsername(username);
	    System.out.println(username);

	    if (user == null) {
	        throw new UsernameNotFoundException("User not found with username: " + username);
	    }

	    List<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

	    UserDetails userDetails = new org.springframework.security.core.userdetails.User(
	        user.getUsername(),
	        user.getPassword(),
	        authorities
	    );

	    return userDetails;
	}


	@Override
	public User save(UserRegistrationDto userRegistrationDto) {
		String hashedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());
		User user = new User(userRegistrationDto.getUsername(),userRegistrationDto.getEmail(),hashedPassword,userRegistrationDto.getPhone(),userRegistrationDto.getRole());
		return repo.save(user);
	}
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}

	public void save(User user) {
		repo.save(user);
		// TODO Auto-generated method stub
		
	}

	
	public User findByid(Integer id) {
	return 	repo.findById(id).get();
	}
	
	
	
	
	 public String getLongNameByCity(String city, String apiKey) {
	        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city + "&key=" + apiKey;

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            JsonNode root = objectMapper.readTree(response.getBody());
	            JsonNode result = root.path("results").get(0);

	            // Find the administrative area level 3 (Jalgaon) component
	            JsonNode adminAreaLevel3Component = null;
	            for (JsonNode component : result.path("address_components")) {
	                JsonNode types = component.path("types");
	                if (types.isArray() && types.size() > 0 && types.get(0).asText().equals("administrative_area_level_3")) {
	                    adminAreaLevel3Component = component;
	                    break;
	                }
	            }

	            if (adminAreaLevel3Component != null) {
	                String longName = adminAreaLevel3Component.path("long_name").asText();
	                return longName;
	            } else {
	                return "Administrative Area Level 3 not found.";
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "Error retrieving long name.";
	        }
	    }
	
	
}
