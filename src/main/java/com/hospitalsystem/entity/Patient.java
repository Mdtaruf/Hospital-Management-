package com.hospitalsystem.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Enter RegNo ")
	@Size(min = 3, max=3,message = "Registration Must be Under Three digit")
	@Pattern(regexp = "[0-9]+", message = "Enter Integer Value")
	@Column(unique = true)
	private String regNo;

	@NotEmpty(message = "Enter Name")
	@Size(min = 3, message = "Name Must Be Atleast Three Character")
	@Pattern(regexp = "[a-zA-Z ]+", message = "Enter Valid Name")
	private String name;

	@NotEmpty(message = "Enter Age")
	@Pattern(regexp = "[0-9]+", message = "Enter Valid Age")
	@Size(min = 1, max = 2, message = "Enter Valid Age")
	private String age;

	@NotEmpty(message = "Enter Gender")
	@Pattern(regexp = "(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE|$)", message = "Enter valid Gender male or female")
	private String gender;

	@NotEmpty(message = "Enter Mobile Number")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number Must be 1 - 9 And Ten digit Only")
	private String mobile;

	@Size(min = 5, message = "Enter Full Address")
	@NotEmpty(message = "Enter Address")
	private String address;

	@Size(min = 3, message = "Name Must Be Atleast Three Character")
	@Pattern(regexp = "[a-zA-Z ]+", message = "Enter Valid Name")
	@NotEmpty(message = "Enter GuardianName")
	private String guardianName;

	@Pattern(regexp = "(?:m|M|mother|MOTHER|f|F|father|Father|FATHER|Friend|Sister|Son|Other|Mother|$)", message = "Please Enter valid Relation Like ''Father', 'Mother' , 'Other'")
	@NotEmpty(message = "Enter Relation")
	private String relation;

	@Positive(message = "Invalid")
	@NotNull(message = "Enter regFee")
	@Pattern(regexp = "[0-9]+", message = "Enter Integer Value")
	@Size(min = 3, max = 3, message = "Registration fee Start from 100 to 999")
	private String regFee;
	
//	@Positive()
//	private long feeId;

	@JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
	@JsonSerialize()
	@JsonDeserialize()
	private LocalDateTime regDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize()
	@JsonDeserialize()
	private LocalDateTime expDate;

//	@NotEmpty(message = "Enter Doctor Name")
//	private String doctorName;
	
	@Pattern(regexp = "[0-9]+", message = "Enter Integer Value")
	private String totalBill;

	private String uHID;

	public String getuHID() {
		return uHID;
	}

	public void setuHID(String uHID) {
		this.uHID = uHID;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRegFee() {
		return regFee;
	}

	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public LocalDateTime getExpDate() {
		return expDate;
	}

	public void setExpDate(LocalDateTime plusDays) {
		this.expDate = plusDays;
	}

//	public String getDoctorName() {
//		return doctorName;
//	}
//
//	public void setDoctorName(String doctorName) {
//		this.doctorName = doctorName;
//	}

	public String getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(String totalBill) {
		this.totalBill = totalBill;
	}

	public Patient(long id, @NotEmpty(message = "enter RegNo ") String regNo,
			@NotEmpty(message = "enter name") String name, @NotEmpty(message = "enter age") String age,
			@NotEmpty(message = "enter gender") String gender, @NotEmpty(message = "enter mobile number") String mobile,
			@NotEmpty(message = "enter address") String address,
			@NotEmpty(message = "enter guardianName") String guardianName,
			@NotEmpty(message = "enter relation") String relation, @NotEmpty(message = "enter regFee") String regFee,
			@NotEmpty(message = "enter regDate") LocalDateTime regDate,
			@NotEmpty(message = "enter expDate") LocalDateTime expDate,
			//@NotEmpty(message = "enter doctor Name") String doctorName,
			@NotEmpty(message = "enter uHID number") String uHID, @NotEmpty(message = "enter bill") String totalBill) {
		super();
		this.id = id;
		this.uHID = uHID;
		this.regNo = regNo;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobile = mobile;
		this.address = address;
		this.guardianName = guardianName;
		this.relation = relation;
		this.regFee = regFee;
		this.regDate = regDate;
		this.expDate = expDate;
		//this.doctorName = doctorName;
		this.totalBill = totalBill;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

}
