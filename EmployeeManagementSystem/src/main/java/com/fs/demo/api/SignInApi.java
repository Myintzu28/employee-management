package com.fs.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fs.demo.model.input.SignInForm;
import com.fs.demo.model.output.SignInResult;
import com.fs.demo.service.ApiSecurityService;
import com.fs.demo.utils.response.ApiResponse;

@RestController
@RequestMapping("public")
public class SignInApi {
	
	@Autowired
	private ApiSecurityService service;

	@PostMapping("signin")
	ApiResponse<SignInResult> signIn(
			@Validated @RequestBody SignInForm form, BindingResult result) {
		return ApiResponse.success(service.signIn(form));
	}
}
