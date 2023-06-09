package com.hospitalsystem.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.hospitalsystem.entity.Doctors;

public interface DoctorService {

	ResponseEntity<?> addDoctor(@Valid Doctors doctor);

	ResponseEntity<?> getAll();

	ResponseEntity<?> deleteDoctor(long id);

	ResponseEntity<?> updateDoctor(long id, Doctors doctor);

}
