package com.hospitalsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hospitalsystem.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {
	@Query("select u from Patient u where u.regNo=:regNo")
	Optional<Patient> findByRegNo(@Param("regNo") String regNo);

	boolean existsByuHID(String s);

	Optional<Patient>  findByuHID(String uhid);

}
