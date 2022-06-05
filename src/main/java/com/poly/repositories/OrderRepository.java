package com.poly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("select c from Order c where c.accountById.id = :id")
	List<Order> findAllByAccountId(@Param("id") Integer id);

}
