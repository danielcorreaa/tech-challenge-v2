package com.techchallenge.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.techchallenge.infrastructure.persistence.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	List<ProductEntity> findByCategory(String category);
	
	List<ProductEntity> findByIdIn(@Param("ids") List<Long> ids);

}
