package com.fs.demo.model.input;

import jakarta.validation.constraints.NotBlank;

public record DepartmentFormForUpdate(
		@NotBlank(message = "Please enter department name.")
		String name,
		String description) {

}