package com.fs.demo.service;

import static com.fs.demo.utils.helper.EntityOperationHelper.created;
import static com.fs.demo.utils.helper.EntityOperationHelper.getOne;
import static com.fs.demo.utils.helper.EntityOperationHelper.updated;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fs.demo.model.entity.Department;
import com.fs.demo.model.input.DepartmentFormForCreate;
import com.fs.demo.model.input.DepartmentFormForManagerChanges;
import com.fs.demo.model.input.DepartmentFormForUpdate;
import com.fs.demo.model.input.DepartmentSearch;
import com.fs.demo.model.output.DepartmentInfo;
import com.fs.demo.model.output.DepartmentInfoDetails;
import com.fs.demo.model.repo.DepartmentRepo;
import com.fs.demo.model.repo.EmployeeRepo;
import com.fs.demo.utils.exceptions.ApiBusinessException;
import com.fs.demo.utils.response.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class DepartmentService {
	
	private static final String DOMAIN_NAME = "Department";

	private static final String EMPLOYEE_DOMAIN = "Employee";
	
	@Autowired
	private DepartmentRepo repo;
	@Autowired
	private EmployeeRepo employeeRepo;

	public DataModificationResult<String> create(DepartmentFormForCreate form) {
		var entity = repo.save(form.entity());
		return created(entity.getCode(), DOMAIN_NAME);
	}

	public DataModificationResult<String> update(String code, DepartmentFormForUpdate form) {
		var entity = getOne(repo.findById(code), DOMAIN_NAME, code);
		entity.setName(form.name());
		entity.setDescription(form.description());
		return updated(code, DOMAIN_NAME);
	}

	public DataModificationResult<String> update(String code, DepartmentFormForManagerChanges form) {
		var entity = getOne(repo.findById(code), DOMAIN_NAME, code);
		var manager = getOne(employeeRepo.findById(form.headCode()), EMPLOYEE_DOMAIN, form.headCode());
		
		if(!manager.getDepartment().getCode().equals(code)) {
			throw new ApiBusinessException("Employee for head of department is from other department.");
		}
		
		entity.setHeadOfDepartment(manager);
		
		return updated(code, DOMAIN_NAME);
	}

	@Transactional(readOnly = true)
	public DepartmentInfoDetails findById(String code) {
		var entity = getOne(repo.findById(code), DOMAIN_NAME, code);
		return DepartmentInfoDetails.from(entity);
	}

	@Transactional(readOnly = true)
	public List<DepartmentInfo> search(DepartmentSearch search) {
		var queryFunc = queryFunc(search);
		return repo.findAll(queryFunc);
	}

	private Function<CriteriaBuilder, CriteriaQuery<DepartmentInfo>> queryFunc(DepartmentSearch search) {
		return cb -> {
			var cq = cb.createQuery(DepartmentInfo.class);
			var root = cq.from(Department.class);
			
			DepartmentInfo.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.asc(root.get("code")));
			
			return cq;
		};
	}

}