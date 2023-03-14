package com.vedansh.demo.entity;



import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name="UserDetails")
public class UserDetails {

	public UserDetails() {
		super();
	}

	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@jakarta.persistence.Column(name="Id")
	private Long id;
	
	@jakarta.persistence.Column(name="Name")
	private String name;

	@jakarta.persistence.Column(name="Email")
	private String email;
	
	@jakarta.persistence.Column(name="Password")
	private String password;
	
	@jakarta.persistence.Column(name="Role")
    private String role;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public UserDetails(Long id, String name, String email, String password,String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role=role;
	}
	
}
