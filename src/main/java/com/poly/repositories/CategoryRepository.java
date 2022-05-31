package com.poly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.status = 1")
	List<Category> findAllActive();
	
}
