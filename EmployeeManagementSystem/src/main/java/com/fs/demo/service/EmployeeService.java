package com.fs.demo.service;

import static com.fs.demo.utils.helper.EntityOperationHelper.created;
import static com.fs.demo.utils.helper.EntityOperationHelper.getOne;
import static com.fs.demo.utils.helper.EntityOperationHelper.noChanges;
import static com.fs.demo.utils.helper.EntityOperationHelper.updated;

import java.time.LocalDate;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fs.demo.model.entity.Employee;
import com.fs.demo.model.entity.Employee.Status;
import com.fs.demo.model.input.EmployeeFormForCreate;
import com.fs.demo.model.input.EmployeeFormForUpdate;
import com.fs.demo.model.input.EmployeeFormForUpdateStatus;
import com.fs.demo.model.input.EmployeeSearch;
import com.fs.demo.model.output.EmployeeInfo;
import com.fs.demo.model.output.EmployeeInfoDetails;
import com.fs.demo.model.repo.DepartmentRepo;
import com.fs.demo.model.repo.EmployeeRepo;
import com.fs.demo.utils.helper.EmployeeCodeGenerator;
import com.fs.demo.utils.response.DataModificationResult;
import com.fs.demo.utils.response.Pager;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class EmployeeService {
	
	private static final String DOMAIN_NAME = "Employee";
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private EmployeeCodeGenerator codeGenerator;

	public DataModificationResult<String> create(EmployeeFormForCreate form) {
		
		var code = codeGenerator.next(form.department());
		var password = encoder.encode("123456");
		
		var entity = form.entity(code, password);
		var depertment = getOne(departmentRepo.findById(form.department()), "Department", form.department());
	
		entity.setDepartment(depertment);
		
		entity = employeeRepo.saveAndFlush(entity);
		
		return created(code, DOMAIN_NAME);
	}

	public DataModificationResult<String> update(String code, EmployeeFormForUpdate form) {
		var entity = getOne(employeeRepo.findById(code), DOMAIN_NAME, code);
		
		if(isNeedToChange(entity, form)) {
			entity.getAccount().setName(form.name());
			entity.setPhone(form.phone());
			entity.setEmail(form.email());
			entity.setGender(form.gender());
			entity.setDateOfBirth(form.dob());
			entity.setAssignDate(form.assignDate());
			entity.setRemark(form.remark());
			
			entity = employeeRepo.saveAndFlush(entity);
			
			return updated(code, DOMAIN_NAME);
		}
		
		return noChanges(code, DOMAIN_NAME);		
	}

	public DataModificationResult<String> update(String code, EmployeeFormForUpdateStatus form) {
		var entity = getOne(employeeRepo.findById(code), DOMAIN_NAME, code);
		
		if(entity.getStatus() != form.status()) {
			entity.setStatus(form.status());
			entity.setRemark(form.remark());
			
			if(form.status() == Status.Retired) {
				entity.setRetireDate(LocalDate.now());
			}
			
			if(form.status() == Status.Permenant) {
				entity.setProbationPassDate(LocalDate.now());
			}

			entity = employeeRepo.saveAndFlush(entity);

			return updated(code, DOMAIN_NAME);
		}
		
		return noChanges(code, DOMAIN_NAME);
	}

	@Transactional(readOnly = true)
	public EmployeeInfoDetails findById(String code) {
		var entity = getOne(employeeRepo.findById(code), DOMAIN_NAME, code);
		return EmployeeInfoDetails.from(entity);
	}

	@Transactional(readOnly = true)
	public Pager<EmployeeInfo> search(EmployeeSearch search, int page, int size) {
		
		var queryFunc = queryFunc(search);
		var countFunc = countFunc(search);
		var pageResult = employeeRepo.search(queryFunc, countFunc, page, size);
		
		return Pager.from(pageResult);
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(EmployeeSearch search) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Employee.class);
			cq.select(cb.count(root.get("code")));
			cq.where(search.where(cb, root));
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<EmployeeInfo>> queryFunc(EmployeeSearch search) {
		return cb -> {
			var cq = cb.createQuery(EmployeeInfo.class);
			var root = cq.from(Employee.class);
			EmployeeInfo.select(cq, root);
			cq.where(search.where(cb, root));
			cq.orderBy(cb.asc(root.get("code")));
			return cq;
		};
	}

	private boolean isNeedToChange(Employee entity, EmployeeFormForUpdate form) {
		return !form.name().equals(entity.getAccount().getName()) 
				|| !form.dob().equals(entity.getDateOfBirth())
				|| !form.phone().equals(entity.getPhone())
				|| !form.email().equals(entity.getEmail())
				|| !form.gender().equals(entity.getGender())
				|| !form.assignDate().equals(entity.getAssignDate())
				|| !form.remark().equals(entity.getRemark());
	}


}
