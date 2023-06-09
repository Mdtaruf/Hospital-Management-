package com.hospitalsystem.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalsystem.entity.UserLogin;
import com.hospitalsystem.entity.Users;
import com.hospitalsystem.service.UserService;



@RestController
public class MainController {
	
	@Autowired
	private UserService userService;
	
	
	// THIS API FOR ACCOUNTENT REGISTRATION
	
	@PostMapping("/accountentRegister")
	public ResponseEntity<?> accountentRegistration(@Valid @RequestBody Users user){
		
		ResponseEntity<?> acRegister=userService.saveAccountent(user);
		return acRegister;
		
		
	}
	
	
	// THIS API FOR RECEPTIONIST REGISTRATION
	@PostMapping("/receptionistRegister")
	public ResponseEntity<?> receptionistRegistration(@Valid @RequestBody Users user){
		ResponseEntity<?> saveReceptionist = userService.saveReceptionist(user);
		return saveReceptionist;
	}
	

	// THIS API FOR ADMIN REGISTRATION
	@PostMapping("/adminRegister")
	public ResponseEntity<?> adminRegistration(@Valid @RequestBody Users user){
		ResponseEntity<?> saveAdmin=userService.saveAdmin(user);
		return saveAdmin;
		
	}
	
	
	
	// THIS API FOR ACCOUNTENT LOGIN
	
	@PostMapping("/login")
	public ResponseEntity<?> accountentLogin(@RequestBody UserLogin userLogin) throws Exception{
		
		ResponseEntity<?> login=userService.findByUsernameAndPassword(userLogin.getUsername(),userLogin.getPassword());
		return login;
	}
	
	
	//	THIS API FOR FIND ALL 
	
	@GetMapping("fetchAllEmploye")
	public ResponseEntity<?> fetchAllEmploy(){
		
		ResponseEntity<?> fetchAll=userService.findAllEmploye();
		return fetchAll;
		
		
	}
	
	
	// THIS API FOR FIND BY ACCOUNTENT
	@GetMapping("/getByRole/{role}")
	public ResponseEntity<?> fetchByRole(@PathVariable String role){
		
		ResponseEntity<?> users=userService.findByRole(role);
		return users;
		
		
	}
	
	// FIND BY ID 
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> FindById(@PathVariable long id){
		
		ResponseEntity<?> user=userService.findById(id);
		return user;
	}
	
	
	
	// THIS API FOR DELETE EMPLOY BY ID
	
	@DeleteMapping("/deletById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id){
		
		ResponseEntity<?> delete=userService.deletById(id);
		return delete;
	
	}
	
	
	// THIS API FOR UPDATE RECORD PUT MAPPING
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable long id,@Valid@RequestBody Users user){
		
		ResponseEntity<?> usr=userService.updateById(id,user);
		return usr;
		
	}
	
	
	// THIS API FOR UPDATE USING PATCH MAPPING
	
	@PatchMapping("/patchUpdate/{id}")
	public ResponseEntity<?> updateByIdUsingPatchMapping(@PathVariable long id, @Valid@RequestBody Users user){
		ResponseEntity<?> usr=userService.updateByPatch(id,user);
		return usr;
		
	}
	
}
