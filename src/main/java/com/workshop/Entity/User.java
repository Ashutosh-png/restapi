package com.workshop.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Builder
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String email;
	private String password;
	private String phone;
	
	@Enumerated(EnumType.STRING)
	  private Role role;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	


	public User(int id, String username, String email, String password, String phone, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}

	public User(String username, String email, String password, String phone, Role role) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	 public static Builder builder() {
		    return new Builder();
		  }

		  public static class Builder {

		    private String username;
		    private String email;
		    private String password;
		    private String phone;
		    private Role role;

		    public Builder username(String username) {
		      this.username = username;
		      return this;
		    }

		    public Builder email(String email) {
		      this.email = email;
		      return this;
		    }

		    public Builder password(String password) {
		      this.password = password;
		      return this;
		    }

		    public Builder phone(String phone) {
		      this.phone = phone;
		      return this;
		    }

		    public Builder role(Role role) {
		      this.role = role;
		      return this;
		    }

		    public User build() {
		      return new User(username, email, password, phone, role);
		    }
		  }

	public enum Role {
	    USER,
	    VENDOR,
	    ADMIN
	  }
	
	

}
