package com.fs.demo.model.repo;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fs.demo.model.BaseRepository;
import com.fs.demo.model.entity.Account;

@Repository
public interface AccountRepo extends BaseRepository<Account, Long> {

	Optional<Account> findOneByUsername(String string);
	
}
