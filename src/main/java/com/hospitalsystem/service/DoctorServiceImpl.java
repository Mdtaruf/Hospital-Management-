package com.hospitalsystem.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hospitalsystem.entity.Doctors;
import com.hospitalsystem.entity.FeeCategory;
import com.hospitalsystem.repository.DoctoreRepository;
import com.hospitalsystem.repository.FeeCatRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctoreRepository doctorRepo;
	@Autowired
	private FeeCatRepository feeCatRepo;

	@Override
	public ResponseEntity<?> addDoctor(@Valid @RequestBody Doctors doctor) {
		Map<String, Object> map = new LinkedHashMap<>();
		Optional<FeeCategory> category = feeCatRepo.findByCatName(doctor.getSpecialist());
		FeeCategory feeCategory = category.get();
		if (category.isPresent()) {
			doctor.setFeeId(feeCategory.getFeeId());
			doctorRepo.save(doctor);
			map.put("Status", "True");
			map.put("message", "Successfully Register");
			map.put("Data", doctor);
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		} else {
			map.put("Status", "Fail");
			map.put("message", "Enter valid Speciality");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<?> getAll() {
		Map<String, Object> map = new LinkedHashMap<>();
		List<Doctors> doctors = doctorRepo.findAll();
		if (doctors.isEmpty()) {
			map.put("Status", "Fail");
			map.put("message", "List is Empty");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		} else {
			map.put("Status", "True");
			map.put("message", "Data fatch Successfully");
			map.put("Data", doctors);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

	}

	@Override
	public ResponseEntity<?> deleteDoctor(long id) {
		Map<String, String> map = new LinkedHashMap<>();
		Optional<Doctors> doctor = doctorRepo.findById(id);
		if (doctor.isPresent()) {
			Doctors doctors = doctor.get();
			doctorRepo.deleteById(doctors.getDocterId());
			map.put("Status", "True");
			map.put("String", "Successfully Deleted");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.put("String", "Fail");
			map.put("message", "Data Not Found");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<?> updateDoctor(long id, Doctors doctor) {
		Map<String,Object> map=new LinkedHashMap<>();
		Optional<Doctors> doctorr = doctorRepo.findById(id);
		if(doctorr.isPresent()) {
			Doctors doctors = doctorr.get();
			doctors.setDocName(doctor.getDocName());
			doctors.setSpecialist(doctor.getSpecialist());
			Doctors save = doctorRepo.save(doctors);
			map.put("Status","True");
			map.put("message","Update Successfully");
			map.put("Data", save);
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
		}else {
			
			map.put("Status", "Fail");
			map.put("message", "Data Not Found");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		
	}

}
