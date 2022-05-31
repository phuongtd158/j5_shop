package com.poly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	@Query("select c from Order c where c.status = 1")
	List<Order> findAllActive();
	
}
