package com.hospitalsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalsystem.entity.FeeCategory;

public interface FeeCatRepository extends JpaRepository<FeeCategory,Long> {

	public Optional<FeeCategory> findByCatName(String specialist);

}
