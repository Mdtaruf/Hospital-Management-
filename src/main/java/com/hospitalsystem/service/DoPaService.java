package com.hospitalsystem.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.hospitalsystem.entity.DoPaRelation;

public interface DoPaService {

	ResponseEntity<?> addDoPaRelation(@Valid DoPaRelation doPoRelation);

}
