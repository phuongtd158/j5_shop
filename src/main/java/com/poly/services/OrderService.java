package com.poly.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.poly.entities.Order;

public interface OrderService {

	<S extends Order> List<S> findAll(Example<S> example, Sort sort);

	<S extends Order> List<S> findAll(Example<S> example);

	Order getReferenceById(Integer id);

	Order getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Order> entities);

	Order getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Order, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Order entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Order> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Order> entities);

	<S extends Order> long count(Example<S> example);

	void deleteInBatch(Iterable<Order> entities);

	<S extends Order> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Order> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Order> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Order> List<S> saveAll(Iterable<S> entities);

	Optional<Order> findById(Integer id);

	List<Order> findAllById(Iterable<Integer> ids);

	List<Order> findAll(Sort sort);

	Page<Order> findAll(Pageable pageable);

	List<Order> findAll();

	<S extends Order> Optional<S> findOne(Example<S> example);

	<S extends Order> S save(S entity);

}
