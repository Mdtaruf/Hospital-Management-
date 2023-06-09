package com.hospitalsystem.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hospitalsystem.entity.FeeCategory;
import com.hospitalsystem.entity.Users;
import com.hospitalsystem.repository.FeeCatRepository;
import com.hospitalsystem.repository.UserRepo;

@Service
public class FeeCatServiceImpl implements FeeCatService {
	@Autowired
	private FeeCatRepository feeCatRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	public ResponseEntity<?> addFeeCat(FeeCategory feeCat) {
		Map<String, Object> map = new LinkedHashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Users user = userRepo.findByUsername(name);
		if (user.getRole().equals("Admin")) {
			FeeCategory save = feeCatRepo.save(feeCat);
			map.put("Status", "True");
			map.put("message", "Successfully Save");
			map.put("Data", save);
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		} else {
			map.put("Status", "Fail");
			map.put("message", "Only Admin Can Add Fee Category");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getAllFeeCat() {
		Map<String, Object> map = new LinkedHashMap<>();
		List<FeeCategory> findAll = feeCatRepo.findAll();
		if (findAll.isEmpty()) {
			map.put("Status", "Fail");
			map.put("message", "List Is Empty");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		} else {
			map.put("Status", "True");
			map.put("message", "Successfully Fatch Data");
			map.put("Data", findAll);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> deleteFeeCat(long id) {
		Map<String, String> map = new LinkedHashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Users user = userRepo.findByUsername(name);
		Optional<FeeCategory> category = feeCatRepo.findById(id);
		FeeCategory feeCategory = category.get();
		if (user.getRole().equals("Admin")) {
			if (category.isPresent()) {
				feeCatRepo.deleteById(feeCategory.getFeeId());
				map.put("Status", "true");
				map.put("message", "Delete Successfully");
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {

				map.put("Status", "Fail");
				map.put("message", "Data Not Found");
				return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
			}
		} else {
			map.put("Status", "Fail");
			map.put("message", "Admin Can Delete");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<?> updateCategory(long id, FeeCategory category) {
		Map<String, String> map = new LinkedHashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Users user = userRepo.findByUsername(name);
		Optional<FeeCategory> categoryy = feeCatRepo.findById(id);
		FeeCategory feeCategory = categoryy.get();
		if (user.getRole().equals("Admin")) {
			if (categoryy.isPresent()) {
				feeCategory.setCatName(category.getCatName());
				feeCategory.setAmount(category.getAmount());
				feeCategory.setStatus(category.getStatus());
				feeCatRepo.save(feeCategory);
				map.put("Status", "True");
				map.put("message", "update successfully");
				return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
			} else {

				map.put("Status", "Fail");
				map.put("message", "Data Not Found");
				return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
			}
		} else {
			map.put("Status", "Fail");
			map.put("message", "Only Admin Can Update");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

		}

	}

}
