package com.fs.demo.utils.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fs.demo.model.repo.DepartmentRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
@Transactional(readOnly = true)
public class DepartmentCodeForUniqueValidator implements ConstraintValidator<DepartmentCodeForUnique, String>{

	@Autowired
	private DepartmentRepo repo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return repo.countByCode(value) == 0L;
	}
}
