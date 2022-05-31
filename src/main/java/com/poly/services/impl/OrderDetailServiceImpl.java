package com.poly.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.poly.entities.OrderDetail;
import com.poly.repositories.OrderDetailRepository;
import com.poly.services.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	
	private OrderDetailRepository detailRepository;

	public OrderDetailServiceImpl(OrderDetailRepository detailRepository) {
		this.detailRepository = detailRepository;
	}

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return detailRepository.save(entity);
	}

	@Override
	public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
		return detailRepository.findOne(example);
	}

	@Override
	public List<OrderDetail> findAll() {
		return detailRepository.findAll();
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return detailRepository.findAll(pageable);
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return detailRepository.findAll(sort);
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Integer> ids) {
		return detailRepository.findAllById(ids);
	}

	@Override
	public Optional<OrderDetail> findById(Integer id) {
		return detailRepository.findById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return detailRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		detailRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return detailRepository.existsById(id);
	}

	@Override
	public <S extends OrderDetail> S saveAndFlush(S entity) {
		return detailRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return detailRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return detailRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<OrderDetail> entities) {
		detailRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends OrderDetail> long count(Example<S> example) {
		return detailRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderDetail> entities) {
		detailRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return detailRepository.count();
	}

	@Override
	public <S extends OrderDetail> boolean exists(Example<S> example) {
		return detailRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		detailRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		detailRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(OrderDetail entity) {
		detailRepository.delete(entity);
	}

	@Override
	public <S extends OrderDetail, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return detailRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		detailRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		detailRepository.deleteAllInBatch();
	}

	@Override
	public OrderDetail getOne(Integer id) {
		return detailRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		detailRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		detailRepository.deleteAll();
	}

	@Override
	public OrderDetail getById(Integer id) {
		return detailRepository.getById(id);
	}

	@Override
	public OrderDetail getReferenceById(Integer id) {
		return detailRepository.getReferenceById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example) {
		return detailRepository.findAll(example);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return detailRepository.findAll(example, sort);
	}
}
