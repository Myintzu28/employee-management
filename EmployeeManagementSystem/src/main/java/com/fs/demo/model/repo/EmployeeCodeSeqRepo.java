package com.fs.demo.model.repo;

import org.springframework.stereotype.Repository;

import com.fs.demo.model.BaseRepository;
import com.fs.demo.model.entity.EmployeeCodeSeq;

@Repository
public interface EmployeeCodeSeqRepo extends BaseRepository<EmployeeCodeSeq, String> {

}
