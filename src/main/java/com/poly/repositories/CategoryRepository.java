package com.poly.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.status = 1")
	List<Category> findAllActive();
	
	@Query("select c from Category c where c.status = 1")
	Page<Category> findAllActive(Pageable pageable);
	
	@Query("select c from Category c where c.status = 1")
	List<Category> findAllActive(Sort sort);
}
