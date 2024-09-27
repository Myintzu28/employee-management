package com.fs.demo.model.output;

import java.util.List;

import lombok.Data;

@Data
public class SignInResult {

	private String loginId;
	private String name;
	private String token;
	private List<String> authorities;
	private boolean activated;

}
