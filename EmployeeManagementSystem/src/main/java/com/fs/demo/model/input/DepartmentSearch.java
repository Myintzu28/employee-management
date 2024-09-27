package com.fs.demo.model.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.fs.demo.model.entity.Department;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record DepartmentSearch(
		String department,
		String head) {

	public Predicate [] where(CriteriaBuilder cb, Root<Department> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(department)) {
			var param = department.toLowerCase().concat("%");
			list.add(cb.or(
				cb.like(cb.lower(root.get("code")), param),
				cb.like(cb.lower(root.get("name")), param)
			));
		}
 		
		if(StringUtils.hasLength(head)) {
			var employee = root.join("headOfDepartment", JoinType.LEFT);
			var param = head.toLowerCase().concat("%");
			list.add(cb.or(
				cb.like(cb.lower(employee.get("code")), param),
				cb.like(cb.lower(employee.get("account").get("name")), param)
			));
		}

		return list.toArray(size -> new Predicate[size]);
	}
}