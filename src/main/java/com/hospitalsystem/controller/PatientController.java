package com.hospitalsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalsystem.entity.Patient;
import com.hospitalsystem.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	
	// THIS API FOR PATIENTS REGISTRATIONS
	@PostMapping("/savePatient")
	public ResponseEntity<?> savePatient(@Valid @RequestBody Patient patient){
		ResponseEntity<?> save = patientService.savePatient(patient);
		return save;
	}

	// THIS API FOR FETCH ALL PATIENT DETAILS
	@GetMapping("/getAllPatient")
	public ResponseEntity<?> fetchAllDetail(){
		ResponseEntity<?> fetchAll=patientService.fetchAllDetails();
		return fetchAll;
	}
	
	// THIS API FOR FETCH RECORD BY REGISTRATION NUMBER
	@GetMapping("/getByRegNo/{regNo}")
	public ResponseEntity<?> fetchByRegNo(@PathVariable String regNo){
		ResponseEntity<?> fetchByRegNo=patientService.fetchByRegNo(regNo);
		return fetchByRegNo;
	}
	
	// THIS API FOR DELETE RECORD BY REGISTRATION NUMBER
	@DeleteMapping("/deletByRegId/{regNo}")
	public ResponseEntity<?> deleteByRegNo(@PathVariable String regNo){
		ResponseEntity<?> deleteByRegNo=patientService.deletByRegNo(regNo);
		return deleteByRegNo;
	}
	
	// THIS API FOR UPDATE RECORD BY REGISTRATION NUMBER
	@PutMapping("/updateByRegId/{regNo}")
	public ResponseEntity<?> updateByRegNo(@PathVariable String regNo, @Valid@RequestBody Patient patient){
		ResponseEntity<?> updateByRegNo=patientService.updatByRegNo(regNo,patient);
		return updateByRegNo;
	}
	
	// THIS API FOR PATCH UPDATE(for fill bill)
	@PatchMapping("/totaBillUpdate/{uhid}")
	public ResponseEntity<?> forBillUpdate(@PathVariable String uhid ,@Valid@RequestBody Patient patient){
		ResponseEntity<?> billUpdate=patientService.billUpdate(uhid,patient);
		return billUpdate;
	}
	
}
