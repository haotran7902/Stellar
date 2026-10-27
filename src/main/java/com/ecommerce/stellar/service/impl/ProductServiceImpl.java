package com.ecommerce.stellar.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.stellar.dto.request.ProductRequest;
import com.ecommerce.stellar.dto.request.ProductPatchRequest;
import com.ecommerce.stellar.dto.response.ProductResponse;
import com.ecommerce.stellar.entity.Category;
import com.ecommerce.stellar.entity.Product;
import com.ecommerce.stellar.exception.DuplicateSkuException;
import com.ecommerce.stellar.exception.ResourceNotFoundException;
import com.ecommerce.stellar.repository.CategoryRepository;
import com.ecommerce.stellar.repository.ProductRepository;
import com.ecommerce.stellar.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public ProductResponse create(ProductRequest request) {
		if (productRepository.existsBySku(request.sku())) {
			throw new DuplicateSkuException(request.sku());
		}

		Product product = new Product();
		applyRequest(product, request);
		return toResponse(productRepository.save(product));
	}

	@Transactional(readOnly = true)
	public List<ProductResponse> findAll() {
		return productRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public ProductResponse findById(Long productId) {
		return toResponse(getProduct(productId));
	}

	public ProductResponse update(Long productId, ProductRequest request) {
		Product product = getProduct(productId);
		if (productRepository.existsBySkuAndProductIdNot(request.sku(), productId)) {
			throw new DuplicateSkuException(request.sku());
		}

		applyRequest(product, request);
		return toResponse(productRepository.save(product));
	}

	@Override
	public ProductResponse patch(Long productId, ProductPatchRequest request) {
		Product product = getProduct(productId);
		if (request.sku() != null && productRepository.existsBySkuAndProductIdNot(request.sku(), productId)) {
			throw new DuplicateSkuException(request.sku());
		}

		if (request.productName() != null) {
			product.setProductName(request.productName());
		}
		if (request.description() != null) {
			product.setDescription(request.description());
		}
		if (request.image() != null) {
			product.setImage(request.image());
		}
		if (request.sku() != null) {
			product.setSku(request.sku());
		}
		if (request.price() != null) {
			product.setPrice(request.price());
		}
		if (request.categoryId() != null) {
			Category category = categoryRepository.findById(request.categoryId())
					.orElseThrow(() -> new ResourceNotFoundException("Category", request.categoryId()));
			product.setCategory(category);
		}
		return toResponse(productRepository.save(product));
	}

	public void delete(Long productId) {
		productRepository.delete(getProduct(productId));
	}

	private Product getProduct(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", productId));
	}

	private void applyRequest(Product product, ProductRequest request) {
		Category category = categoryRepository.findById(request.categoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category", request.categoryId()));
		product.setProductName(request.productName());
		product.setDescription(request.description());
		product.setImage(request.image());
		product.setSku(request.sku());
		product.setPrice(request.price());
		product.setCategory(category);
	}

	private ProductResponse toResponse(Product product) {
		return new ProductResponse(
				product.getProductId(),
				product.getProductName(),
				product.getDescription(),
				product.getImage(),
				product.getSku(),
				product.getPrice(),
				product.getCategory().getCategoryId(),
				product.getCategory().getCategoryName());
	}
}
