package com.fs.demo.utils.response;

public record DataModificationResult<ID>(
		ID id,
		String message
		) {

}