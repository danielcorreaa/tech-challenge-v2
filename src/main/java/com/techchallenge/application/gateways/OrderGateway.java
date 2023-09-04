package com.techchallenge.application.gateways;

import java.util.List;
import java.util.Optional;

import com.techchallenge.domain.entity.Order;

public interface OrderGateway {
	
	Order insert(Order order);

	Optional<List<Order>> findAll(int page, int size, List<String> sort);

	Optional<List<Order>> findByStatusOrder(String recebido);

	Optional<Order> findbyId(Long id);

	List<Order> findByStatusAndDate();


}
