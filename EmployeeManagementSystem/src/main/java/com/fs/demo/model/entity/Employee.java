package com.fs.demo.model.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(indexes = { 
		@Index(columnList = "assign_date") }
)
public class Employee {

	@Id
	private String code;

	@OneToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Account account;

	@ManyToOne(optional = false)
	private Department department;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@Column(nullable = false, name = "assign_date")
	private LocalDate assignDate;

	private LocalDate probationPassDate;
	private LocalDate retireDate;

	private String remark;

	public enum Status {
		Probation, Permenant, Retired
	}

	public enum Gender {
		Male, Female
	}

}
