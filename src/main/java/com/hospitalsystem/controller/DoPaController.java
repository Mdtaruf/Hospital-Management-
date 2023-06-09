package com.hospitalsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalsystem.entity.DoPaRelation;
import com.hospitalsystem.service.DoPaService;

@RestController
public class DoPaController {
	
	@Autowired
	private DoPaService doPaService;
	
	@PostMapping("/addDoPoRelation")
	private ResponseEntity<?> doPaRelation(@Valid@RequestBody DoPaRelation doPoRelation){
		ResponseEntity<?> add= doPaService.addDoPaRelation(doPoRelation);
		return add;
	}

}
