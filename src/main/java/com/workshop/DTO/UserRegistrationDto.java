package com.workshop.DTO;

import com.workshop.Entity.User.Role;

public class UserRegistrationDto {
	
	private String username;
	private String email;
	private String password;
	private String phone;
	private Role role;
	public String getUsername() {
		return username;
	}
	public void setName(String name) {
		this.username = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
