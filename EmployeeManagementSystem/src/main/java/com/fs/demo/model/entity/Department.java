package com.fs.demo.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Department {

	@Id
	private String code;

	@Column(nullable = false, unique = true)
	private String name;

	private String description;

	@ManyToOne
	private Employee headOfDepartment;

	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

}
