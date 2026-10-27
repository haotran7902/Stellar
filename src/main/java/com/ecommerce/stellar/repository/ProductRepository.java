package com.ecommerce.stellar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.stellar.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	boolean existsBySku(String sku);

	boolean existsBySkuAndProductIdNot(String sku, Long productId);

	boolean existsByCategoryCategoryId(Long categoryId);
}
