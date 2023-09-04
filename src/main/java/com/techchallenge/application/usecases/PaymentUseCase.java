package com.techchallenge.application.usecases;

import com.techchallenge.domain.entity.Payment;

public interface PaymentUseCase {

	Payment validPayment(Long idOrder, String webhook);
	
	Payment findByOrder(Long idOrder);

	Payment webhook(String resource);
	
}
