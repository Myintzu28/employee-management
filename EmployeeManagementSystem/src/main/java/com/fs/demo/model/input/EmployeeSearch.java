package com.fs.demo.model.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.fs.demo.model.entity.Employee;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record EmployeeSearch(
		LocalDate from, 
		LocalDate to,
		String department,
		String name
		) {

	public Predicate[] where(CriteriaBuilder cb, Root<Employee> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get("assignDate"), from));
		}
		
		if(null != to) {
			list.add(cb.lessThanOrEqualTo(root.get("assignDate"), to));
		}
		
		if(StringUtils.hasLength(department)) {
			list.add(
				cb.or(
					cb.equal(root.get("department").get("code"), department),
					cb.like(cb.lower(root.get("department").get("name")), 
							department.toLowerCase().concat("%"))
				)	
			);
		}

		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get("account").get("name")), name.toLowerCase().concat("%")));
		}

		return list.toArray(size -> new Predicate[size]);
	}
}
