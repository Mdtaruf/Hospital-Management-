package com.hospitalsystem.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.hospitalsystem.entity.Users;

public interface UserService {
	
	public ResponseEntity<?> saveAccountent(Users users);

	public ResponseEntity<?> findByUsernameAndPassword(String username, String password) throws Exception;

	public ResponseEntity<?> saveReceptionist(Users user);

    public ResponseEntity<?> saveAdmin(@Valid Users user);

    public ResponseEntity<?> findAllEmploye();

	public ResponseEntity<?> findByRole(String role);

	public ResponseEntity<?> findById(long id);

	public ResponseEntity<?> deletById(long id);

	public ResponseEntity<?> updateById(long id, Users user);

	public ResponseEntity<?> updateByPatch(long id, Users user);


}
