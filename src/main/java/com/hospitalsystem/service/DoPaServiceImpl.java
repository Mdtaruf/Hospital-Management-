package com.hospitalsystem.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hospitalsystem.entity.DoPaRelation;
import com.hospitalsystem.entity.Doctors;
import com.hospitalsystem.entity.Patient;
import com.hospitalsystem.repository.DoPaRepository;
import com.hospitalsystem.repository.DoctoreRepository;
import com.hospitalsystem.repository.PatientRepo;

@Service
public class DoPaServiceImpl implements DoPaService {

	@Autowired
	private DoPaRepository doPaRepository;
	@Autowired
	private PatientRepo patientRepo;
	@Autowired
	private DoctoreRepository doctoreRepo;

	@Override
	public ResponseEntity<?> addDoPaRelation(@Valid DoPaRelation doPaRelation) {
    Map<String,Object> map=new LinkedHashMap<>();
    Optional<Patient> patients = patientRepo.findById(doPaRelation.getPationId());
    Optional<Doctors> doctors = doctoreRepo.findByDocterId(doPaRelation.getDoctorId());
    
  if(patients.isPresent() && doctors.isPresent()) {
    Patient patient = patients.get();
    Doctors doctor = doctors.get();
    doPaRelation.setPationId(patient.getId());
    doPaRelation.setDoctorId(doctor.getDocterId());
	DoPaRelation save = doPaRepository.save(doPaRelation);
    map.put("Status", "True");
    map.put("message", "Successfully Add");
    map.put("Data", save);
	return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
	}
  else {
	  
	  map.put("Status", "Fail");
	  map.put("message", "Enter Valid Id");
	  return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
  }
	
	}
	
	
	
	

}
