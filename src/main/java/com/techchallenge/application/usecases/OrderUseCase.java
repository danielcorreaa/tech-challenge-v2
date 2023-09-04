package com.techchallenge.application.usecases;

import java.util.List;

import com.techchallenge.domain.entity.Order;

public interface OrderUseCase {

	Order insert(String cpf, List<Long> productsId);	
	Order findById(Long id);
	List<Order> findAll(int page, int size, List<String> sort);
	List<Order> findAllByStatusAndDate();
	Order ready(Long id);
	Order finish(Long id);

}
