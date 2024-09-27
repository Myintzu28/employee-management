package com.fs.demo.model.output;

import java.time.LocalDate;

import com.fs.demo.model.entity.Employee;
import com.fs.demo.model.entity.Employee.Gender;
import com.fs.demo.model.entity.Employee.Status;

public record EmployeeInfoDetails(
		String code,
		String name,
		String loginId,
		String phone,
		String email,
		LocalDate dob,
		Gender gender,
		String departmentCode,
		String department,
		Status status,
		LocalDate assignDate,
		LocalDate probationPassDate,
		LocalDate retiredDate,
		String remark) {

	public static EmployeeInfoDetails from(Employee entity) {
		return new EmployeeInfoDetails(
			entity.getCode(), 
			entity.getAccount().getName(), 
			entity.getAccount().getUsername(), 
			entity.getPhone(), 
			entity.getEmail(), 
			entity.getDateOfBirth(), 
			entity.getGender(), 
			entity.getDepartment().getCode(),
			entity.getDepartment().getName(), 
			entity.getStatus(), 
			entity.getAssignDate(), 
			entity.getProbationPassDate(),
			entity.getRetireDate(), 
			entity.getRemark()
			);
	}
}
