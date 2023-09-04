package com.techchallenge.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techchallenge.infrastructure.persistence.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {		
	
	@Query("Select o from OrderEntity o ORDER BY o.dateOrderInit ASC ")
	Page<OrderEntity> findAllOrderByDateOrderInitAsc(Pageable pageable);

	Optional<List<OrderEntity>> findByStatusOrder(String recebido);

	@Query("Select o from OrderEntity o where o.statusOrder <> 'FINALIZADO' ORDER BY FIELD(o.statusOrder, 'PRONTO', 'EM_PREPARACAO', 'RECEBIDO'), dateOrderInit ASC ")
	Optional<List<OrderEntity>> findByStatusAndDate();

	
}
