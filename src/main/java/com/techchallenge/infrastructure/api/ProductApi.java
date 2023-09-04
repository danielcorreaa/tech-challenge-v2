package com.techchallenge.infrastructure.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.techchallenge.application.usecases.ProductUseCase;
import com.techchallenge.config.infra.Result;
import com.techchallenge.domain.entity.Product;
import com.techchallenge.infrastructure.api.mapper.ProductMapper;
import com.techchallenge.infrastructure.api.request.InsertProductRequest;
import com.techchallenge.infrastructure.api.request.ProductResponse;
import com.techchallenge.infrastructure.api.request.UpdateProductRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/products")
public class ProductApi {

	private ProductUseCase productUseCase;
	private ProductMapper mapper;

	public ProductApi(ProductUseCase productUseCase, ProductMapper mapper) {
		super();
		this.productUseCase = productUseCase;
		this.mapper = mapper;
	}

	@PostMapping
	public ResponseEntity<Result<ProductResponse>> insert(@RequestBody @Valid InsertProductRequest request,
			UriComponentsBuilder uri) {
		Product product = productUseCase.insert(mapper.toProduct(request));
		UriComponents uriComponents = uri.path("/api/v1/products/find/{id}").buildAndExpand(product.getId());
		return ResponseEntity.created(uriComponents.toUri()).body(Result.create(mapper.toProductResponse(product)));
	}

	@PutMapping
	public ResponseEntity<Result<ProductResponse>> update(@RequestBody @Valid UpdateProductRequest request) {
		Product product = productUseCase.update(mapper.toProduct(request));
		return ResponseEntity.ok(Result.ok(mapper.toProductResponse(product)));
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Result<String>> delete(@PathVariable Long id) {
		productUseCase.delete(id);
		return ResponseEntity.ok(Result.ok("Delete with success!"));
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<Result<List<ProductResponse>>> findByCategory(@PathVariable String category) {
		List<Product> product = productUseCase.findByCategory(category);
		return ResponseEntity.ok(Result.ok(mapper.toProductResponseList(product)));
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Result<Product>> findById(@PathVariable Long id) {
		Product product = productUseCase.findById(id);
		return ResponseEntity.ok(Result.ok(product));
	}

}
