package com.fs.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fs.demo.model.entity.Account;
import com.fs.demo.model.entity.Account.Role;
import com.fs.demo.model.repo.AccountRepo;

@Configuration
public class ApiAdminUserConfiguration {
	
	@Autowired
	private AccountRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			if(repo.count() == 0L) {
				var admin = new Account();
				admin.setName("Admin User");
				admin.setUsername("admin");
				admin.setPassword(encoder.encode("adminpass"));
				admin.setRole(Role.Admin);
				admin.setActivated(true);
				repo.save(admin);
			}
		};
	}
}