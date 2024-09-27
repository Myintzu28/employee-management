package com.fs.demo.model.repo;

import org.springframework.stereotype.Repository;

import com.fs.demo.model.BaseRepository;
import com.fs.demo.model.entity.Employee;

@Repository
public interface EmployeeRepo extends BaseRepository<Employee, String> {

}
