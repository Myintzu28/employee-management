package com.fs.demo.model.output;

import com.fs.demo.model.entity.Department;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record DepartmentInfo(
		String code,
		String name,
		String managerCode,
		String managerName,
		String managerPhone,
		long employees) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<DepartmentInfo> cq, Root<Department> root) {
		
		var manager = root.join("headOfDepartment", JoinType.LEFT);
		var employee = root.join("employees", JoinType.LEFT);
		var account = employee.join("account", JoinType.LEFT);
		
		cq.multiselect(
			root.get("code"),
			root.get("name"),
			manager.get("code"),
			account.get("name"),
			manager.get("phone"),
			cb.count(employee.get("code"))
		);
		
		cq.groupBy(
			root.get("code"),
			root.get("name"),
			manager.get("code"),
			account.get("name"),
			manager.get("phone")
		);
	}
}