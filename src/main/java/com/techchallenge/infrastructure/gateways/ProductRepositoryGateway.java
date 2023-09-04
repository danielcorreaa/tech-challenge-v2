package com.techchallenge.infrastructure.gateways;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.techchallenge.application.gateways.ProductGateway;
import com.techchallenge.domain.entity.Product;
import com.techchallenge.infrastructure.persistence.entity.ProductEntity;
import com.techchallenge.infrastructure.persistence.mapper.ProductEntityMapper;
import com.techchallenge.infrastructure.persistence.repository.ProductRepository;

@Component
public class ProductRepositoryGateway implements ProductGateway {

	private ProductRepository repository;

	private ProductEntityMapper mapper;

	public ProductRepositoryGateway(ProductRepository repository, ProductEntityMapper mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Product insert(Product product) {
		ProductEntity productEntity = repository.save(mapper.toProductEntity(product));
		return mapper.toProduct(productEntity);
	}

	@Override
	public Product update(Product product) {			
		ProductEntity productEntity = repository.save(mapper.toProductEntity(product));
		return mapper.toProduct(productEntity);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<List<Product>> findByCategory(String category) {
		var findByCategory = repository.findByCategory(category);
		if(findByCategory.isEmpty()) {
			return Optional.empty();
		}
		List<Product> response = findByCategory.stream().map(entity -> mapper.toProduct(entity))
			.collect(Collectors.toList());
		return Optional.of(response);
		
	}

	@Override
	public Optional<Product> findById(Long id) {
		var productEntity =  repository.findById(id);
		return productEntity.map( entity -> mapper.toProduct(entity));
	}

	@Override
	public Optional<List<Product>> findByIds(List<Long> productsId) {
		var findByIdIn = repository.findByIdIn(productsId);
		if(findByIdIn.isEmpty()) {
			return Optional.empty();
		}
		List<Product> response = findByIdIn.stream().map(entity -> mapper.toProduct(entity))
				.collect(Collectors.toList());
		return Optional.of(response);
	}

}
