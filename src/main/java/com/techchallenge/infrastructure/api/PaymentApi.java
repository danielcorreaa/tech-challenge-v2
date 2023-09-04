package com.techchallenge.infrastructure.api;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.techchallenge.application.usecases.PaymentUseCase;
import com.techchallenge.config.infra.Result;
import com.techchallenge.domain.entity.Payment;
import com.techchallenge.infrastructure.api.mapper.PaymentMapper;
import com.techchallenge.infrastructure.api.request.PaymentRequest;
import com.techchallenge.infrastructure.api.request.PaymentResponse;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentApi {

	private PaymentUseCase paymentUseCase;
	private PaymentMapper mapper;

	public PaymentApi(PaymentUseCase paymentUseCase, PaymentMapper mapper) {
		super();
		this.paymentUseCase = paymentUseCase;
		this.mapper = mapper;
	}

	@GetMapping("/pay/{id}")
	public ResponseEntity<InputStreamResource> checkout(@PathVariable Long id, UriComponentsBuilder uri ) throws IOException {
		
		UriComponents uriComponents = uri.path("/api/v1/payment/webhook").build();
		System.out.println(uriComponents.toUriString());
		Payment payment = paymentUseCase.validPayment(id, uriComponents.toUriString());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(payment.getQrCode()));
	}

	@GetMapping("/find/order/{id}")
	public ResponseEntity<Result<PaymentResponse>> findbyOrder(@PathVariable Long id) throws IOException {
		Payment payment = paymentUseCase.findByOrder(id);
		return ResponseEntity.ok(Result.ok(mapper.toPaymentResponse(payment)));

	}
	
	@PostMapping("/webhook")
	public ResponseEntity<Result<PaymentResponse>> webhook(@RequestBody PaymentRequest request) throws IOException {
		Payment payment = paymentUseCase.webhook(request.resource());
		return ResponseEntity.ok(Result.ok(mapper.toPaymentResponse(payment)));

	}

}
