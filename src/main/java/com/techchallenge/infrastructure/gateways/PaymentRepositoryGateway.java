package com.techchallenge.infrastructure.gateways;

import org.springframework.stereotype.Component;

import com.techchallenge.application.gateways.PaymentGateway;
import com.techchallenge.domain.entity.Payment;
import com.techchallenge.domain.errors.NotFoundException;
import com.techchallenge.infrastructure.persistence.entity.PaymentEntity;
import com.techchallenge.infrastructure.persistence.mapper.PaymentEntityMapper;
import com.techchallenge.infrastructure.persistence.repository.PaymentRepository;

@Component
public class PaymentRepositoryGateway implements PaymentGateway {

	private PaymentRepository paymentRepository;
	private PaymentEntityMapper mapper;

	public PaymentRepositoryGateway(PaymentRepository paymentRepository, PaymentEntityMapper mapper) {
		super();
		this.paymentRepository = paymentRepository;
		this.mapper = mapper;
	}

	@Override
	public Payment insert(Payment payment) {
		PaymentEntity paymentEntity = mapper.toPaymentEntity(payment);
		if(paymentRepository.findByOrderId(paymentEntity.getOrder().getId()).isPresent()) {
			return mapper.toPayment(paymentEntity);
		}
		paymentEntity = paymentRepository.save(paymentEntity);
		return mapper.toPayment(paymentEntity);
	}

	@Override
	public Payment findByOrder(Long id) {
		PaymentEntity paymentByOrder = paymentRepository.findByOrderId(id).orElseThrow(() -> new NotFoundException("Payment not found"));	
		return mapper.toPayment(paymentByOrder);		
	}

	@Override
	public Payment update(Payment payment) {
		PaymentEntity paymentEntity = mapper.toPaymentEntity(payment);
		paymentEntity = paymentRepository.save(paymentEntity);
		return mapper.toPayment(paymentEntity);
	}

	@Override
	public int updateStatusPayment(Long externalReferencelong) {
		return paymentRepository.updateStatusPayment(externalReferencelong);
	}

}
