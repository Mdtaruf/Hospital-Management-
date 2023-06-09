package com.hospitalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalsystem.entity.DoPaRelation;

public interface DoPaRepository extends JpaRepository<DoPaRelation, Long> {
	

}
