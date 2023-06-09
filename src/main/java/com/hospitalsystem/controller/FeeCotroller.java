package com.hospitalsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalsystem.entity.FeeCategory;
import com.hospitalsystem.service.FeeCatService;

@RestController
public class FeeCotroller {
	@Autowired
	private FeeCatService feeCatService;
	
	@PostMapping("/addFeeCat")
	public ResponseEntity<?> addFeeCategory(@Valid@RequestBody FeeCategory feeCat){
		ResponseEntity<?> addFeeCat=feeCatService.addFeeCat(feeCat);
		return addFeeCat;
	}
    @GetMapping("/getAllFeeCat")
	public ResponseEntity<?> fetchAllCategory(){
    	ResponseEntity<?> allFeeCat=feeCatService.getAllFeeCat();
    	return allFeeCat;
	}
	
    @DeleteMapping("/deletFeeCat/{id}")
    public ResponseEntity<?> deletFeeCat(@PathVariable long id){
    	ResponseEntity<?> deleteFeeCat=feeCatService.deleteFeeCat(id);
    	return deleteFeeCat;
    }

    @PutMapping("/updatFeeCat/{id}")
    public ResponseEntity<?> updateRecord(@PathVariable long id,@Valid@RequestBody FeeCategory category){
    	ResponseEntity<?> updateRecord=feeCatService.updateCategory(id,category);
    	return updateRecord;
    }
    
}
