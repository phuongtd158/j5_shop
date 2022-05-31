package com.poly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("select c from Product c where c.status = 1")
	List<Product> findAllActive();
	
}
