package com.ecommerce.stellar.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.stellar.dto.request.CategoryRequest;
import com.ecommerce.stellar.dto.request.CategoryPatchRequest;
import com.ecommerce.stellar.dto.response.CategoryResponse;
import com.ecommerce.stellar.entity.Category;
import com.ecommerce.stellar.exception.CategoryAlreadyExistsException;
import com.ecommerce.stellar.exception.CategoryHasProductsException;
import com.ecommerce.stellar.exception.ResourceNotFoundException;
import com.ecommerce.stellar.repository.CategoryRepository;
import com.ecommerce.stellar.repository.ProductRepository;
import com.ecommerce.stellar.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	@Override
	public CategoryResponse create(CategoryRequest request) {
		if (categoryRepository.existsByCategoryName(request.categoryName())) {
			throw new CategoryAlreadyExistsException(request.categoryName());
		}

		Category category = new Category();
		applyRequest(category, request);
		return toResponse(categoryRepository.save(category));
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoryResponse> findAll() {
		return categoryRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public CategoryResponse findById(Long categoryId) {
		return toResponse(getCategory(categoryId));
	}

	@Override
	public CategoryResponse update(Long categoryId, CategoryRequest request) {
		Category category = getCategory(categoryId);
		if (categoryRepository.existsByCategoryNameAndCategoryIdNot(request.categoryName(), categoryId)) {
			throw new CategoryAlreadyExistsException(request.categoryName());
		}

		applyRequest(category, request);
		return toResponse(categoryRepository.save(category));
	}

	@Override
	public CategoryResponse patch(Long categoryId, CategoryPatchRequest request) {
		Category category = getCategory(categoryId);
		if (request.categoryName() != null
				&& categoryRepository.existsByCategoryNameAndCategoryIdNot(request.categoryName(), categoryId)) {
			throw new CategoryAlreadyExistsException(request.categoryName());
		}

		if (request.categoryName() != null) {
			category.setCategoryName(request.categoryName());
		}
		if (request.description() != null) {
			category.setDescription(request.description());
		}
		return toResponse(categoryRepository.save(category));
	}

	@Override
	public void delete(Long categoryId) {
		Category category = getCategory(categoryId);
		if (productRepository.existsByCategoryCategoryId(categoryId)) {
			throw new CategoryHasProductsException(categoryId);
		}
		categoryRepository.delete(category);
	}

	private Category getCategory(Long categoryId) {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));
	}

	private void applyRequest(Category category, CategoryRequest request) {
		category.setCategoryName(request.categoryName());
		category.setDescription(request.description());
	}

	private CategoryResponse toResponse(Category category) {
		return new CategoryResponse(
				category.getCategoryId(),
				category.getCategoryName(),
				category.getDescription());
	}
}
