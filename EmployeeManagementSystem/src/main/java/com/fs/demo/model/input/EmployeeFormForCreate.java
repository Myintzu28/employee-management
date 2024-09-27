package com.fs.demo.model.input;

import java.time.LocalDate;

import com.fs.demo.model.entity.Account;
import com.fs.demo.model.entity.Account.Role;
import com.fs.demo.model.entity.Employee;
import com.fs.demo.model.entity.Employee.Gender;
import com.fs.demo.model.entity.Employee.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeFormForCreate(
		@NotBlank(message = "Please enter employee name.")
		String name,
		@NotBlank(message = "Please enter department.")
		String department,
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
		@NotNull(message = "Please select status.")
		Status status,
		String remark
		) {

	public Employee entity(String code, String password) {
		var employee = new Employee();
		employee.setCode(code);
		
		var account = new Account();
		account.setUsername(code);
		account.setRole(Role.Employee);
		account.setPassword(password);
		account.setName(name);
		
		employee.setAccount(account);
		employee.setPhone(phone);
		employee.setEmail(email);
		employee.setGender(gender);
		employee.setDateOfBirth(dob);
		employee.setStatus(status);
		employee.setAssignDate(assignDate);
		
		if(status == Status.Permenant) {
			employee.setProbationPassDate(assignDate);
		}
		
		employee.setRemark(remark);
		
		return employee;
	}
}
