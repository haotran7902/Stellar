package com.ecommerce.stellar.service;

import java.util.List;

import com.ecommerce.stellar.dto.request.ProductRequest;
import com.ecommerce.stellar.dto.request.ProductPatchRequest;
import com.ecommerce.stellar.dto.response.ProductResponse;

public interface ProductService {
	ProductResponse create(ProductRequest request);

	List<ProductResponse> findAll();

	ProductResponse findById(Long productId);

	ProductResponse update(Long productId, ProductRequest request);

	ProductResponse patch(Long productId, ProductPatchRequest request);

	void delete(Long productId);
}
