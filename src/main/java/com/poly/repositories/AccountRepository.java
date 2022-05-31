package com.poly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Query("select c from Account c where c.activated = 1")
	List<Account> findAllActive();
	
}
