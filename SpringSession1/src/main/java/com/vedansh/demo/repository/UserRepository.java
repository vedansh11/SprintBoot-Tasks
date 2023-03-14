package com.vedansh.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedansh.demo.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Long>{
	public  UserDetails findByEmail(String email);
	 public boolean existsByEmail(String email);
}


