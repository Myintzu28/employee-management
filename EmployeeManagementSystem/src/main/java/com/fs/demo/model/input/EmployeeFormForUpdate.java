package com.fs.demo.model.input;

import java.time.LocalDate;

import com.fs.demo.model.entity.Employee;
import com.fs.demo.model.entity.Employee.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeFormForUpdate(
		@NotBlank(message = "Please enter employee name.")
		String name,
		@NotBlank(message = "Please enter phone.")
		String phone,
		@NotBlank(message = "Please enter email.")
		String email,
		@NotNull(message = "Please select gender.")
		Gender gender,
		@NotNull(message = "Please enter date of birth.")
		LocalDate dob,
		@NotNull(message = "Please enter assign date.")
		LocalDate assignDate,
		String remark) {

	public static EmployeeFormForUpdate from(Employee entity) {
		return new EmployeeFormForUpdate(
				entity.getAccount().getName(), 
				entity.getPhone(), 
				entity.getEmail(), 
				entity.getGender(), 
				entity.getDateOfBirth(), 
				entity.getAssignDate(), 
				entity.getRemark());
	}
}
