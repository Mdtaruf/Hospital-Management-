package com.hospitalsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "enter name")
	@Size(min = 3, message = "name should have atleast 3 character")
	private String name;

	@Column(unique = true)
	@Pattern(regexp = "[a-zA-Z ]+", message = "Enter Valid Name")
	@NotEmpty(message = "enter username")
	@Size(min = 3, message = "username should have atleast 3 character")
	private String username;

	@Column(unique = true)
	@Email(message = "enter valid email")
	@NotEmpty(message = "enter email")
	private String email;

	@Column(unique = true)
	@NotEmpty(message = "enter mobile number")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 1 - 9 And Ten digit Only")
	private String mobile;

	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "pasword must be 1 UperCase letter 1 LowerCase letter 1 Special Caracter 1 Digit")
	@NotEmpty(message = "enter password")
	private String password;

	@NotEmpty(message = "enter Address")
	@Size(min = 6, message = "enter full address")
	private String address;

	// @JsonIgnore
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	// @JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Users(long id, String name, String username, String email, String mobile, String password, String address,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.address = address;
		this.role = role;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

}
