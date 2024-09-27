package com.fs.demo.utils.response;

import java.util.List;

import org.springframework.data.domain.Page;

public record Pager<T>(
		long totalPages,
		long totalElements,
		int page,
		int size,
		List<T> list) {

	public static<T> Pager<T> from(Page<T> result) {
		return new Pager<T>(
				result.getTotalPages(),
				result.getTotalElements(), 
				result.getNumber(), 
				result.getSize(), 
				result.getContent());
	}

}
