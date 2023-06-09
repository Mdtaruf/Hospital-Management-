package com.hospitalsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalsystem.entity.Doctors;
import com.hospitalsystem.service.DoctorService;

@RestController
public class DoctorController {
	
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/addDoctor")
	public ResponseEntity<?> addDoctor(@Valid @RequestBody Doctors doctor){
		ResponseEntity<?> addDoctor=doctorService.addDoctor(doctor);
		return addDoctor;
	}
	
	@GetMapping("/getAllDoctor")
	public ResponseEntity<?> getAllDoctor(){
		ResponseEntity<?> getAll=doctorService.getAll();
		return getAll;
	}
	
	@DeleteMapping("/delDocterById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id){
		ResponseEntity<?> delete=doctorService.deleteDoctor(id);
		return delete;
	}

	@PutMapping("/updateDocter")
	public ResponseEntity<?> deleteById(@PathVariable long id,@Valid@RequestBody Doctors doctor){
		ResponseEntity<?> update=doctorService.updateDoctor(id,doctor);
		return update;
		
	}
}
