package com.fs.demo.model.repo;

import org.springframework.stereotype.Repository;

import com.fs.demo.model.BaseRepository;
import com.fs.demo.model.entity.Department;

@Repository
public interface DepartmentRepo extends BaseRepository<Department, String> {

	long countByCode(String code);
	
}
