package com.ecommerce.stellar.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.stellar.dto.request.CategoryRequest;
import com.ecommerce.stellar.dto.request.CategoryPatchRequest;
import com.ecommerce.stellar.dto.response.CategoryResponse;
import com.ecommerce.stellar.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponse create(@Valid @RequestBody CategoryRequest request) {
		return categoryService.create(request);
	}

	@GetMapping
	public List<CategoryResponse> findAll() {
		return categoryService.findAll();
	}

	@GetMapping("/{categoryId}")
	public CategoryResponse findById(@PathVariable Long categoryId) {
		return categoryService.findById(categoryId);
	}

	@PutMapping("/{categoryId}")
	public CategoryResponse update(@PathVariable Long categoryId, @Valid @RequestBody CategoryRequest request) {
		return categoryService.update(categoryId, request);
	}

	@PatchMapping("/{categoryId}")
	public CategoryResponse patch(@PathVariable Long categoryId, @Valid @RequestBody CategoryPatchRequest request) {
		return categoryService.patch(categoryId, request);
	}

	@DeleteMapping("/{categoryId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long categoryId) {
		categoryService.delete(categoryId);
	}
}
