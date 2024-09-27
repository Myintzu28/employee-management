package com.fs.demo.model;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepositoryImplementation<T, ID> {

	<R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFun,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFun, int page, int size);

	<R> List<R> findAll(Function<CriteriaBuilder, CriteriaQuery<R>> queryFun);

}