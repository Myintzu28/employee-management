package com.fs.demo.model.input;

import com.fs.demo.model.entity.Employee.Status;

import jakarta.validation.constraints.NotNull;

public record EmployeeFormForUpdateStatus(
		@NotNull(message = "Please select status.")
		Status status,
		String remark
		) {

}
