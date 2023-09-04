package com.techchallenge.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techchallenge.infrastructure.persistence.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>{

   Optional<PaymentEntity> findByOrderId(Long id);

   @Transactional
   @Modifying
   @Query("Update PaymentEntity pe set pe.status = 'APROVADO' where pe.order.id = :externalReferencelong")
   int updateStatusPayment(@Param("externalReferencelong") Long externalReferencelong);

}
