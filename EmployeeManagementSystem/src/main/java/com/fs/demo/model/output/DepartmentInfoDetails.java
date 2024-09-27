package com.fs.demo.model.output;

import java.util.List;

import com.fs.demo.model.entity.Department;

public record DepartmentInfoDetails(
		String code,
		String name,
		String description, 
		EmployeeInfo hod,
		List<EmployeeInfo> employees) {
	
	public static DepartmentInfoDetails from(Department entity) {
		return new DepartmentInfoDetails(
				entity.getCode(), 
				entity.getName(), 
				entity.getDescription(), 
				null != entity.getHeadOfDepartment() ? EmployeeInfo.from(entity.getHeadOfDepartment()) : null, 
				entity.getEmployees().stream().map(EmployeeInfo::from).toList()
				);
	}

}