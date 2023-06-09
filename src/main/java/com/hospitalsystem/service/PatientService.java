package com.hospitalsystem.service;

import org.springframework.http.ResponseEntity;

import com.hospitalsystem.entity.Patient;

public interface PatientService {

	public ResponseEntity<?> savePatient(Patient patient);

	public ResponseEntity<?> fetchAllDetails();

	public ResponseEntity<?> fetchByRegNo(String regNo);

	public ResponseEntity<?> deletByRegNo(String regNo);

	public ResponseEntity<?> updatByRegNo(String regNo, Patient patient);

	public ResponseEntity<?> billUpdate(String uhid, Patient patient);

}
