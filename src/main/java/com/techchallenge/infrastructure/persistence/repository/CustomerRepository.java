package com.techchallenge.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techchallenge.infrastructure.persistence.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

	Optional<CustomerEntity> findByCpf(String cpf);

}
