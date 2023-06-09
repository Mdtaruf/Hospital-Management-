package com.hospitalsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hospitalsystem.entity.Doctors;

public interface DoctoreRepository extends JpaRepository<Doctors, Long> {
	@Query("select u from Doctors u where u.DocterId=:doctorId")
	Optional<Doctors> findByDocterId(@Param("doctorId")long doctorId);

}
