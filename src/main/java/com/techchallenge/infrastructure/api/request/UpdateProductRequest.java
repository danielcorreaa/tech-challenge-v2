package com.techchallenge.infrastructure.api.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public record UpdateProductRequest(

		@Getter	
		@NotNull(message = "Id is required!")
		Long id,

		@Getter
		@NotBlank(message = "Title is required!")
		String title, 
		
		@Getter
		@NotBlank(message = "Category is required!")
		String category,
		
		@Getter		
		@NotBlank(message = "Description is required!")
		String description,
		
		@Getter
		@DecimalMin(value = "0.1", inclusive = false, message = "Price can't be zero")
	    @Digits(integer=3, fraction=2)
		BigDecimal price,	
		
		@Getter		
		String image ) {

}
