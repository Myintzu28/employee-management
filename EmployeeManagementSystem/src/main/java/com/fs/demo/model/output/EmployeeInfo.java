package com.fs.demo.model.output;

import java.time.LocalDate;

import com.fs.demo.model.entity.Employee;
import com.fs.demo.model.entity.Employee.Status;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record EmployeeInfo(
		String code,
		String name,
		String phone,
		String department,
		Status status,
		LocalDate assignDate,
		LocalDate probationPassDate,
		LocalDate retiredDate,
		String remark) {
	
	public static void select(CriteriaQuery<EmployeeInfo> cq, Root<Employee> root) {
		cq.multiselect(
			root.get("code"),
			root.get("account").get("name"),
			root.get("phone"),
			root.get("department").get("name"),
			root.get("status"),
			root.get("assignDate"),
			root.get("probationPassDate"),
			root.get("retireDate"),
			root.get("remark")
		);
	}

	public static EmployeeInfo from(Employee entity) {
		return new EmployeeInfo(
			entity.getCode(), 
			entity.getAccount().getName(), 
			entity.getPhone(), 
			entity.getDepartment().getName(), 
			entity.getStatus(), 
			entity.getAssignDate(), 
			entity.getProbationPassDate(),
			entity.getRetireDate(), 
			entity.getRemark());
	}
}