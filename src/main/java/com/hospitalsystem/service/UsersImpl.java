package com.hospitalsystem.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hospitalsystem.entity.Users;
import com.hospitalsystem.repository.UserRepo;
import com.hospitalsystem.util.JwtUtil;

@Service
public class UsersImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtUtil jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	// ADMIN REGISTRATION

	@Override
	public ResponseEntity<?> saveAdmin(@Valid Users user) {

		Map<String, Object> map = new LinkedHashMap<>();
		String role = "Admin";
		user.setRole(role);
		@Valid
		Users save = userRepo.save(user);
		map.put("Status", "True");
		map.put("Message", "Admin Register");
		map.put("Details", save);

		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// ACCOUNTENT REGISTRATION

	@Override
	public ResponseEntity<?> saveAccountent(Users user) {
		Map<String, Object> map = new LinkedHashMap<>();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		Users userr = userRepo.findByUsername(name);

		if (userr.getRole().equals("Admin")) {

			String role = "Accountent";
			user.setRole(role);
			Users usr = userRepo.save(user);
			map.put("Registration", "True");
			map.put("Details", usr);
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}

		else {

			map.put("Status", "Fail");
			map.put("message", "Only Admin Can Add Accountent");
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

	}

	// RECEPTIONIST REGISTRATION

	@Override
	public ResponseEntity<?> saveReceptionist(Users user) {
		Map<String, Object> map = new LinkedHashMap<>();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		Users userr = userRepo.findByUsername(name);

		if (userr.getRole().equals("Admin")) {

			String role = "Receptionist";
			user.setRole(role);
			Users usr = userRepo.save(user);
			map.put("Registration", "True");
			map.put("Details", usr);
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}

		else {

			map.put("Status", "Fail");
			map.put("message", "Only Admin Can Add Receptionist");
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

	}

	// LOGIN USERS

	@Override
	public ResponseEntity<?> findByUsernameAndPassword(String username, String password) throws Exception {

		boolean b = userRepo.existsByUsernameAndPassword(username, password);
		Map<String, Object> map = new LinkedHashMap<>();

		if (b == true) {
			try {
				this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			} catch (UsernameNotFoundException e) {
				throw new Exception("Bad credentials");
			} catch (BadCredentialsException e) {
				throw new Exception("Bad credentials");

			}
			UserDetails userdetails = this.userDetailsService.loadUserByUsername(username);

			final String token = this.jwtUtils.generateToken(userdetails);

			map.put("Status", "true");
			map.put("message", "login successfully");
			map.put("your token is:", token);
			return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

		} else {

			map.put("Status", "fail");
			map.put("message", "login fail");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	// FETCH ALL EMPLOYE

	@Override
	public ResponseEntity<?> findAllEmploye() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Users user = userRepo.findByUsername(name);
		if (user.getRole().equals("Admin")) {
			Map<String, Object> map = new LinkedHashMap<>();
			List<Users> findAll = userRepo.findAll();
			map.put("Status", "True");
			map.put("message", "Data Fetch Successfully");
			map.put("Data", findAll);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("Status", "Fail");
			map.put("message", "Only Admin Can see");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	// FETCH EMPLOYEE BY ROLE 
	
	@Override
	public ResponseEntity<?> findByRole(String role) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		String s="Admin";
		Users user = userRepo.findByUsername(name);
		if(user.getRole().equals(s)) {
			List<Users> us=userRepo.findByRole(role);
			Map<String,Object> map=new LinkedHashMap<>();
			if(us.isEmpty()) {
				
				map.put("Status", "Fail");
				map.put("Message", "Data Not Found");
				return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
			}
			
			map.put("Status", "True");
			map.put("Details", us);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			Map<String,Object> map=new LinkedHashMap<>();
			map.put("Status", "Fail");
			map.put("Message", "Only Admin Can See");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
			
		}
			
		

		
	}

	
	// FETCH BY ID
	
	@Override
	public ResponseEntity<?> findById(long id) {
		Map<String,Object> map=new LinkedHashMap<>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		String s="Admin";
		Users userr = userRepo.findByUsername(name);
		if(userr.getRole().equals(s)) {
		Optional<Users> user = userRepo.findById(id);
		
		if(user.isPresent()) {
			map.put("Status", "True");
			map.put("Data", user);
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			
			map.put("Status", "Fail");
			map.put("message", "Data Not Found");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		
	}else {
		map.put("Status", "Fail");
		map.put("message", "Only Admin Can See");
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}

	}

	
	
	// DELETE BY ID
	
	@Override
	public ResponseEntity<?> deletById(long id) {
		Map<String, Object> map=new LinkedHashMap<>(); 
		Optional<Users> user=userRepo.findById(id);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Users userr = userRepo.findByUsername(name);
		if(userr.getRole().equals("Admin")) {
		
		if(user.isPresent()) {
			
			userRepo.deleteById(id);
			map.put("Status", "True");
			map.put("message", "One User Deleted");
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			map.put("Status", "Fail");
			map.put("message", "Data Not Found");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		
	}else {
		map.put("Status", "Fail");
		map.put("message", "Only Admin Can Delete");
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
	}

	
	
	
	// UPDATE BY ID
	
	@Override
	public ResponseEntity<?> updateById(long id, Users user) {
		Map<String,Object> map=new LinkedHashMap<>();
		Optional<Users> usr= userRepo.findById(id);
		Users users = usr.get();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Users userr = userRepo.findByUsername(name);
		
		if(userr.getRole().equals("Admin")) {
			
			if(usr.isPresent()) {
				
				users.setName(user.getName());
				users.setUsername(user.getUsername());
				users.setEmail(user.getEmail());
				users.setPassword(user.getPassword());
				users.setMobile(user.getMobile());
				users.setAddress(user.getAddress());
				userRepo.save(users);
				map.put("Status", "True");
				map.put("message", "Updated Successfully");
				map.put("Data", userr);
				return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
				
			}else {
				map.put("Status", "Fail");
				map.put("mesaage", "Data Not Found");
				return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
				
			}
			
		}else {
			
			map.put("Status", "Fail");
			map.put("message", "Only Admin Can Update");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
			
		}
		
		
	}

	// UPDATE BY ID USING PATCH MAPPING
	@Override
	public ResponseEntity<?> updateByPatch(long id, Users user) {
		
		Map<String,Object> map=new LinkedHashMap<>();
		Optional<Users> usr= userRepo.findById(id);
		Users users = usr.get();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Users userr = userRepo.findByUsername(name);
		if(userr.getRole().equals("Admin")) {
			
			if(usr.isPresent()) {
				users.setUsername(user.getUsername());
				users.setPassword(user.getPassword());
				users.setRole(user.getRole());
				userRepo.save(users);
				map.put("Status", "True");
				map.put("message", "successfully Update");
				map.put("Data", users);
				return new ResponseEntity<>(map,HttpStatus.OK);
			}else {
				
				map.put("Status", "Fail");
				map.put("message", "Data Not Found");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		}else {
			map.put("Status", "Fail");
			map.put("message", "Only Admin can update");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
