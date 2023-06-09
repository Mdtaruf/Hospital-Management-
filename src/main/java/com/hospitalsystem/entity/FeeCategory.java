package com.hospitalsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class FeeCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long feeId;
	
	@Column(unique= true)
	@Pattern(regexp="[a-zA-z ]+",message="Enter Valid Category")
	@NotEmpty(message="Enter Category Type")
	private String catName;
	
	@Pattern(regexp="[0-9]+", message="Enter Valid Amount")
	@NotEmpty(message="Enter Fee Amount")
	private String amount;

	@NotEmpty(message="Enter Status")
	@Pattern(regexp="[a-zA-z ]+",message="Enter Valid Status active or inactive")
	@Pattern(regexp="(?:A|a|Active|active|i|I|Inactive|inactive|INACTIVE|ACTIVE|$)", message="Enter valid Status Active or Inactive")
	private String status;

	public long getFeeId() {
		return feeId;
	}

	public void setFeeId(long id) {
		this.feeId = id;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FeeCategory(long id,
			@Pattern(regexp = "[a-zA-z ]+", message = "Enter Valid Category") @NotEmpty(message = "Enter Category Type") String catName,
			@Pattern(regexp = "[0-9]+", message = "Enter Valid Amount") @NotEmpty(message = "Enter Fee Amount") String amount,
			@NotEmpty(message = "Enter Status") @Pattern(regexp = "[a-zA-z ]+", message = "Enter Valid Category") @Pattern(regexp = "(?:A|a|Active|active|i|I|Inactive|incative|INCATIVE|ACITVE|$)", message = "Enter valid Status Active or Inactive") String status) {
		super();
		this.feeId = id;
		this.catName = catName;
		this.amount = amount;
		this.status = status;
	}

	public FeeCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}