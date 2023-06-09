package com.hospitalsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
public class DoPaRelation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long relId;
	
	@Positive(message="Enter Integer Value")
	private long PationId;
	
	@Positive(message="Enter Integer Value")
	private long doctorId;

	public synchronized long getRelId() {
		return relId;
	}

	public synchronized void setRelId(long relId) {
		this.relId = relId;
	}

	public synchronized long getPationId() {
		return PationId;
	}

	public synchronized void setPationId(long pationId) {
		PationId = pationId;
	}

	public synchronized long getDoctorId() {
		return doctorId;
	}

	public synchronized void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public DoPaRelation(long relId, @Pattern(regexp = "[1-9]+", message = "Enter Valid PatinId") long pationId,
			@Pattern(regexp = "[1-9]+", message = "Enter Valid PatinId") long doctorId) {
		super();
		this.relId = relId;
		PationId = pationId;
		this.doctorId = doctorId;
	}

	public DoPaRelation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
