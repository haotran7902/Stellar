package com.ecommerce.stellar.service;

import java.util.List;

import com.ecommerce.stellar.dto.request.CategoryRequest;
import com.ecommerce.stellar.dto.request.CategoryPatchRequest;
import com.ecommerce.stellar.dto.response.CategoryResponse;

public interface CategoryService {
	CategoryResponse create(CategoryRequest request);

	List<CategoryResponse> findAll();

	CategoryResponse findById(Long categoryId);

	CategoryResponse update(Long categoryId, CategoryRequest request);

	CategoryResponse patch(Long categoryId, CategoryPatchRequest request);

	void delete(Long categoryId);
}
