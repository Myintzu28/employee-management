package com.fs.demo.model;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	private EntityManager entityManager;

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public <R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> search,
			Function<CriteriaBuilder, CriteriaQuery<Long>> count, int page, int size) {

		var countCriteriaQuery = count.apply(entityManager.getCriteriaBuilder());
		var countQuery = entityManager.createQuery(countCriteriaQuery);
		var total = countQuery.getSingleResult();

		var searchCriteriaQuery = search.apply(entityManager.getCriteriaBuilder());

		var searchQuery = entityManager.createQuery(searchCriteriaQuery);
		searchQuery.setFirstResult(page * size);
		searchQuery.setMaxResults(size);

		var list = searchQuery.getResultList();

		return new PageImpl<R>(list, PageRequest.of(page, size), total);
	}

	@Override
	public <R> List<R> findAll(Function<CriteriaBuilder, CriteriaQuery<R>> search) {

		var searchCriteriaQuery = search.apply(entityManager.getCriteriaBuilder());
		var searchQuery = entityManager.createQuery(searchCriteriaQuery);

		return searchQuery.getResultList();
	}

}
