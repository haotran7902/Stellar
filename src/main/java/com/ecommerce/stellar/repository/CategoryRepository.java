package com.ecommerce.stellar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.stellar.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	boolean existsByCategoryName(String categoryName);

	boolean existsByCategoryNameAndCategoryIdNot(String categoryName, Long categoryId);
}
