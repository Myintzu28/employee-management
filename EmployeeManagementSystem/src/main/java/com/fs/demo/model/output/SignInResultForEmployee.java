package com.fs.demo.model.output;

import java.time.LocalDate;

import com.fs.demo.model.entity.Employee.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SignInResultForEmployee extends SignInResult {

	private String departmentCode;
	private String departmentName;
	private String positionCode;
	private String positionName;
	private Status status;
	private LocalDate assignDate;

}
