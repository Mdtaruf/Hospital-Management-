package com.hospitalsystem.service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hospitalsystem.entity.Patient;
import com.hospitalsystem.repository.PatientRepo;

@Service
public class PatientServiceImp implements PatientService {

	@Autowired
	private PatientRepo patientRepo;

	// THIS METHOD FOR SAVE PATIENT
	@Override
	public ResponseEntity<?> savePatient(Patient patient) {
		Map<String, Object> map = new LinkedHashMap<>();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime plusDays = now.plusDays(3);
		Random ran = new Random();
		String s = String.format("%04d", ran.nextInt(10000));
		boolean b = patientRepo.existsByuHID(s);

		if (b == false) {
			patient.setRegDate(now);
			patient.setuHID(s);
			patient.setExpDate(plusDays);
			Patient save = patientRepo.save(patient);
			map.put("Status", "True");
			map.put("message", "Registration Successfully");
			map.put("Data", save);
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		} else {

			map.put("Status", "Fail");
			map.put("message", "This UHID Already Exist Try Again");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	// THIS METHOD FOR FETCHA ALL PATIENT
	@Override
	public ResponseEntity<?> fetchAllDetails() {
		Map<String, Object> map = new LinkedHashMap<>();
		List<Patient> patient = patientRepo.findAll();
		map.put("Status", "True");
		map.put("message", "Data Successfully fatched");
		map.put("Data", patient);
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

	// THIS METHOD FOR FETCH RECORD BY REGISTERATION NUMBER
	@Override
	public ResponseEntity<?> fetchByRegNo(String regNo) {
		Map<String, Object> map = new LinkedHashMap<>();
		Optional<Patient> patient = patientRepo.findByRegNo(regNo);
		if (patient.isPresent()) {

			map.put("Status", "True");
			map.put("message", "Data Fatch Successfully");
			map.put("Data", patient);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {

			map.put("Status", "Fail");
			map.put("message", "Data NOt Found");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	// THIS METHOD FOR DELETE RECORDE BY REGISTRATION NUMBER
	@Override
	public ResponseEntity<?> deletByRegNo(String regNo) {
		Map<String, Object> map = new LinkedHashMap<>();
		Optional<Patient> patient = patientRepo.findByRegNo(regNo);

		if (patient.isPresent()) {
			patientRepo.deleteById(patient.get().getId());
			map.put("Status", "True");
			map.put("message", "Successfully Deleted");
			map.put("Data", patient);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.put("Status", "Fail");
			map.put("message", "Data Not Found");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	// THIS METHOD FOR UPDATE RECORD BY REGISTRATION NUMBER
	@Override
	public ResponseEntity<?> updatByRegNo(String regNo, Patient patient) {
		Map<String, Object> map = new LinkedHashMap<>();
		Optional<Patient> patients = patientRepo.findByRegNo(regNo);

		if (patients.isPresent()) {
			Patient patientss = patients.get();
			patientss.setRegNo(patient.getRegNo());
			patientss.setName(patient.getName());
			patientss.setAge(patient.getAge());
			patientss.setAddress(patient.getAddress());
			patientss.setGender(patient.getGender());
			patientss.setGuardianName(patient.getGuardianName());
			patientss.setRelation(patient.getRelation());
			patientss.setMobile(patient.getMobile());
			patientss.setRegFee(patient.getRegFee());
			patientss.setRegDate(patient.getRegDate());
			patientss.setExpDate(patient.getExpDate());
			patientss.setTotalBill(patient.getTotalBill());
			Patient save = patientRepo.save(patientss);
			map.put("Status", "True");
			map.put("message", " Update Successfully");
			map.put("Data", save);
			return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

		} else {

			map.put("Status", "Fail");
			map.put("message", "Data Not Found");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<?> billUpdate(String uhid, Patient patient) {
		Map<String,Object> map=new LinkedHashMap<>();
		Optional<Patient> patientt = patientRepo.findByuHID(uhid);
		Patient patienttt = patientt.get();
		if(patientt.isPresent()) {
			patienttt.setTotalBill(patient.getTotalBill());
			Patient save = patientRepo.save(patienttt);
			map.put("Status", "True");
			map.put("message", "Successfully Update");
			map.put("patient Details", save);
			return new ResponseEntity<>(map,HttpStatus.CREATED);
			
		}else {
			map.put("Status", "Fail");
			map.put("message", "Data Not Found"
					);
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		
	}

}
