package com.techchallenge.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techchallenge.infrastructure.persistence.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>{

   Optional<PaymentEntity> findByOrderId(Long id);

}
