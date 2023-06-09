package com.hospitalsystem.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospitalsystem.entity.Users;
import com.hospitalsystem.repository.UserRepo;

//import com.validationwithannotation.entity.TestDto;



@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;
	
	//@Autowired
	//private PasswordEncoder bcryptEncoder;	
	   
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.userRepo.getUserByEmailOrUsername(username); //for fetch user
     try {		
		if(user==null) {
			 throw new UsernameNotFoundException("user not found exception"+ username); 
		}else {
		return	new User(user.getUsername(),user.getPassword(),new ArrayList<>());
		}}catch(Exception e) {
			e.toString();
		}
     
    	 //throw new UsernameNotFoundException("user not found");
     
     return null;
     }
     
	

}


