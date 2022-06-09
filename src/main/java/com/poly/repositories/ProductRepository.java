package com.poly.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entities.Order;
import com.poly.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select c from Product c where c.status = 1")
	List<Product> findAllActive();

	@Query("select c from Product c where c.status = 1")
	Page<Product> findAllActive(Pageable pageable);
	
	@Query("select c from Product c where c.status = 1")
	List<Product> findAllActive(Sort sort);
	
//	@Query("select c from Product c where c.categoryByid.id = :id")
//	List<Product> findAllByCategoryId(@Param("id") Integer id);
}
