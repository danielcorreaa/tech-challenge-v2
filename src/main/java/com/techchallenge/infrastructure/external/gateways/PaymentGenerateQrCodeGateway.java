package com.techchallenge.infrastructure.external.gateways;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.techchallenge.application.gateways.PaymentExternalGateway;
import com.techchallenge.domain.entity.Order;
import com.techchallenge.domain.entity.Payment;
import com.techchallenge.domain.errors.BusinessException;
import com.techchallenge.infrastructure.external.MercadoLivreOrders;
import com.techchallenge.infrastructure.external.MercadoLivrePayment;
import com.techchallenge.infrastructure.external.QRCodeGenerator;
import com.techchallenge.infrastructure.external.dtos.OrderResponseML;
import com.techchallenge.infrastructure.external.dtos.PaymentResponseML;
import com.techchallenge.infrastructure.external.mapper.OrdersMLMapper;
import com.techchallenge.infrastructure.external.mapper.PaymentExternalMapper;

@Component
public class PaymentGenerateQrCodeGateway implements PaymentExternalGateway {

	private MercadoLivreOrders mercadoLivreOrders;
	private MercadoLivrePayment mercadoLivrePayment;
	private OrdersMLMapper ordersMLMapper;
	private PaymentExternalMapper paymentMapper;	
	
	@Value("${api.mercadolivr.token}")
	private String token;

	public PaymentGenerateQrCodeGateway(MercadoLivreOrders mercadoLivreOrders, MercadoLivrePayment mercadoLivrePayment,
			OrdersMLMapper ordersMLMapper, PaymentExternalMapper paymentMapper) {
		super();
		this.mercadoLivreOrders = mercadoLivreOrders;
		this.mercadoLivrePayment = mercadoLivrePayment;
		this.ordersMLMapper = ordersMLMapper;
		this.paymentMapper = paymentMapper;
	}

	@Override
	public Payment pay(Order order, String webhook) {
		OrderResponseML response = mercadoLivreOrders.sendOrderToMl("Bearer " + token,
				ordersMLMapper.toOrdersML(order, webhook));
		byte[] qrCodeImage = null;
		try {
			qrCodeImage = QRCodeGenerator.getQRCodeImage(response.QrData(), 200, 200);
		} catch (Exception e) {
			throw new BusinessException("Fail to generate QR Code", e);
		}
		InputStream in = new ByteArrayInputStream(qrCodeImage);
		return paymentMapper.toPayment(in, order);
	}

	@Override
	public Payment checkPayment(String resource) {
		String[] split = resource.split("/");
		String param = split[split.length - 1];
		PaymentResponseML findPayment = mercadoLivrePayment.findPayment("Bearer " + token, param);
		
	    Boolean approved = findPayment.orderStatus().equals("paid");
		
	    return paymentMapper.toPayment(findPayment.externalReference(), approved);
	}

}
