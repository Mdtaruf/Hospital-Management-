package com.hospitalsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
public class Doctors {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long DocterId;
	
	
	@Pattern(regexp="[a-zA-Z ]+",message="Enter Valid Name")
	@NotEmpty(message="Enter Name")
	private String docName;
	
	@Pattern(regexp="(?:R|N|NEUROLOGIST|RADIOLOGIST|D|S|SURGEON|DENTIST|C|A|CARDIOLOGIST|AUDIOLOGIST|Other|$)", message="Please Enter valid Speciality")
	@NotEmpty(message="Enter Specialist")
	private String specialist;
	
	
	private long feeId;

	public long getDocterId() {
		return DocterId;
	}

	public void setDocterId(long docterId) {
		DocterId = docterId;
	}

	

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}

	public long getFeeId() {
		return feeId;
	}

	public void setFeeId(long feeId) {
		this.feeId = feeId;
	}

	

	public Doctors(long docterId,
			@Pattern(regexp = "[a-zA-Z ]+", message = "Enter Valid Name") @NotEmpty(message = "Enter Name") String docName,
			@Pattern(regexp = "(?:R|N|NEUROLOGIST|RADIOLOGIST|D|S|SURGEON|DENTIST|C|A|CARDIOLOGIST|AUDIOLOGIST|Other|$)", message = "Please Enter valid Speciality") @NotEmpty(message = "Enter Specialist") String specialist,
			long feeId) {
		super();
		DocterId = docterId;
		this.docName = docName;
		this.specialist = specialist;
		this.feeId = feeId;
	}

	public Doctors() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
