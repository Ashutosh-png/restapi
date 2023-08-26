package com.workshop.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}
