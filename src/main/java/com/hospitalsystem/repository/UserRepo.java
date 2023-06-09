package com.hospitalsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hospitalsystem.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

	boolean existsByUsernameAndPassword(String username, String password);
	@Query("select u from Users u where u.username=:username or u.email=:username")
	Users getUserByEmailOrUsername(@Param("username")String username);
	Users findByUsername(String name);
	List<Users> findByRole(String string);

}
