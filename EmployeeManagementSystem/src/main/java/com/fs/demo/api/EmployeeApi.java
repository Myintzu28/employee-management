package com.fs.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fs.demo.model.input.EmployeeFormForCreate;
import com.fs.demo.model.input.EmployeeFormForUpdate;
import com.fs.demo.model.input.EmployeeFormForUpdateStatus;
import com.fs.demo.model.input.EmployeeSearch;
import com.fs.demo.model.output.EmployeeInfo;
import com.fs.demo.model.output.EmployeeInfoDetails;
import com.fs.demo.service.EmployeeService;
import com.fs.demo.utils.response.ApiResponse;
import com.fs.demo.utils.response.DataModificationResult;
import com.fs.demo.utils.response.Pager;

@RestController
@RequestMapping("employee")
public class EmployeeApi {

	@Autowired
	private EmployeeService service;

	@GetMapping
	ApiResponse<Pager<EmployeeInfo>> search(EmployeeSearch search,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(search, page, size));
	}

	@PostMapping
	ApiResponse<DataModificationResult<String>> create(@Validated @RequestBody EmployeeFormForCreate form,
			BindingResult result) {
		return ApiResponse.success(service.create(form));
	}

	@PutMapping("{code}")
	ApiResponse<DataModificationResult<String>> update(@PathVariable String code,
			@Validated @RequestBody EmployeeFormForUpdate form, BindingResult result) {
		return ApiResponse.success(service.update(code, form));
	}

	@PutMapping("{code}/status")
	ApiResponse<DataModificationResult<String>> update(@PathVariable String code,
			@Validated @RequestBody EmployeeFormForUpdateStatus form, BindingResult result) {
		return ApiResponse.success(service.update(code, form));
	}

	@GetMapping("{code}")
	ApiResponse<EmployeeInfoDetails> findById(@PathVariable String code) {
		return ApiResponse.success(service.findById(code));
	}

}