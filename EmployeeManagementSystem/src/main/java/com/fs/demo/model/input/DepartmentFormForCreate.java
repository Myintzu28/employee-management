package com.fs.demo.model.input;

import com.fs.demo.model.entity.Department;
import com.fs.demo.utils.validators.DepartmentCodeForUnique;

import jakarta.validation.constraints.NotBlank;

public record DepartmentFormForCreate(
		@DepartmentCodeForUnique(message = "Department code is already used.")
		@NotBlank(message = "Department code must not be blank.")
		String code,
		@NotBlank(message = "Please enter department name.")
		String name,
		String description
		) {

	public Department entity() {
		var entity = new Department();
		entity.setCode(code);
		entity.setName(name);
		entity.setDescription(description);
		return entity;
	}
}