package com.hospitalsystem.service;

import org.springframework.http.ResponseEntity;

import com.hospitalsystem.entity.FeeCategory;

public interface FeeCatService {

	public ResponseEntity<?> addFeeCat(FeeCategory feeCat);

	public ResponseEntity<?> getAllFeeCat();

	public ResponseEntity<?> deleteFeeCat(long id);

	public ResponseEntity<?> updateCategory(long id, FeeCategory category);

}
