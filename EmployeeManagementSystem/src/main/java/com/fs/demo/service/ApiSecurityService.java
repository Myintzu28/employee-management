package com.fs.demo.service;

import static com.fs.demo.utils.helper.EntityOperationHelper.getOne;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fs.demo.config.security.ApiTokenProvider;
import com.fs.demo.model.entity.Account;
import com.fs.demo.model.entity.Account.Role;
import com.fs.demo.model.input.SignInForm;
import com.fs.demo.model.output.SignInResult;
import com.fs.demo.model.output.SignInResultForEmployee;
import com.fs.demo.model.repo.AccountRepo;

@Service
public class ApiSecurityService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ApiTokenProvider tokenProvider;

	@Autowired
	private AccountRepo accountRepo;

	@Transactional(readOnly = true)
	public SignInResult signIn(SignInForm form) {

		var authentication = authenticationManager.authenticate(form.authentication());

		var token = tokenProvider.generate(authentication);

		var account = getOne(accountRepo.findOneByUsername(authentication.getName()), "Account",
				authentication.getName());

		return buildResult(account, token);
	}

	private SignInResult buildResult(Account account, String token) {

		if (account.getRole() == Role.Admin) {
			return buildResultForAdmin(account, token);
		}

		return buildREsultForEmployee(account, token);
	}

	private SignInResult buildREsultForEmployee(Account account, String token) {

		var result = new SignInResultForEmployee();

		result.setActivated(account.isActivated());
		result.setAuthorities(List.of(account.getRole().name()));
		result.setLoginId(account.getUsername());
		result.setName(account.getName());
		result.setToken(token);

		var employee = account.getEmployee();

		result.setAssignDate(employee.getAssignDate());
		result.setDepartmentCode(employee.getDepartment().getCode());
		result.setDepartmentName(employee.getDepartment().getName());
		result.setStatus(employee.getStatus());

		return result;
	}

	private SignInResult buildResultForAdmin(Account account, String token) {
		var result = new SignInResult();
		result.setActivated(account.isActivated());
		result.setAuthorities(List.of(account.getRole().name()));
		result.setLoginId(account.getUsername());
		result.setName(account.getName());
		result.setToken(token);
		return result;
	}

}
