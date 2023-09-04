package com.techchallenge.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String category;
	private String description;
	private BigDecimal price;
	private String image;

	@ManyToMany(mappedBy = "products")
	private List<OrderEntity> orders;

	public ProductEntity(Long id, String title, String category, String description, BigDecimal price, String image) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.description = description;
		this.price = price;
		this.image = image;
	}

}
