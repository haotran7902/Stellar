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

import com.ecommerce.stellar.dto.request.ProductRequest;
import com.ecommerce.stellar.dto.request.ProductPatchRequest;
import com.ecommerce.stellar.dto.response.ProductResponse;
import com.ecommerce.stellar.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse create(@Valid @RequestBody ProductRequest request) {
		return productService.create(request);
	}

	@GetMapping
	public List<ProductResponse> findAll() {
		return productService.findAll();
	}

	@GetMapping("/{productId}")
	public ProductResponse findById(@PathVariable Long productId) {
		return productService.findById(productId);
	}

	@PutMapping("/{productId}")
	public ProductResponse update(@PathVariable Long productId, @Valid @RequestBody ProductRequest request) {
		return productService.update(productId, request);
	}

	@PatchMapping("/{productId}")
	public ProductResponse patch(@PathVariable Long productId, @Valid @RequestBody ProductPatchRequest request) {
		return productService.patch(productId, request);
	}

	@DeleteMapping("/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long productId) {
		productService.delete(productId);
	}
}
