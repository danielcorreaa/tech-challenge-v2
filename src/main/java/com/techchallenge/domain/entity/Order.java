package com.techchallenge.domain.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import com.techchallenge.domain.enums.StatusOrder;
import com.techchallenge.domain.valueobject.Status;

public class Order {

	private Long id;
	private Customer customer;
	private List<Product> products;
	private LocalDateTime dateOrderInit;
	private LocalDateTime dateOrderFinish;
	private Long minutesDurationOrder;
	private StatusOrder statusOrder;	

	public Order() {
		super();
	}
	private Order(Long id, Customer customer, List<Product> products, LocalDateTime dateOrderInit,
			LocalDateTime dateOrdernFinish, String statusOrder) {		
		this.id = id;
		this.customer = customer;
		this.products = products;
		this.dateOrderInit = dateOrderInit;
		this.dateOrderFinish = dateOrdernFinish;		
		try {
			this.statusOrder = StatusOrder.valueOf(statusOrder);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid Status Order!");
		}
	}
	
	public Order startOrder(Customer customer, List<Product> procuts) {		
		this.customer = customer;
		this.products = procuts;
		this.dateOrderInit = LocalDateTime.now();		
		this.statusOrder = Status.received(this);
		return this;
	}
	
	public Order sendToPreparation() {
		this.statusOrder = Status.preparation(this);	
		return this;
	}
	
	public Order ready() {
		this.statusOrder = Status.ready(this);
		return this;
	}

	public Order finishOrder(Long id) {
		this.id = id;
		this.dateOrderFinish = LocalDateTime.now();
		this.statusOrder = Status.finalize(this);
		return this;
	}
	
	public static final Order convert(Long id, Customer customer, List<Product> procuts, LocalDateTime dateOrderInit,
			LocalDateTime dateOrdernFinish, String statusOrder) {		
		return new Order(id, customer, procuts, dateOrderInit, dateOrdernFinish, statusOrder);
	}

	public Long getId() {
		return id;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public List<Product> getProcuts() {
		return products;
	}

	public LocalDateTime getInitOrder() {
		return dateOrderInit;
	}

	public LocalDateTime getFinishOrder() {
		return dateOrderFinish;
	}

	public Long getMinutesDurationOrder() {
		if(Optional.ofNullable(getFinishOrder()).isPresent()) {
			minutesDurationOrder = this.dateOrderInit.until(getFinishOrder(), ChronoUnit.MINUTES);
			return minutesDurationOrder;
		}
		minutesDurationOrder = this.dateOrderInit.until(LocalDateTime.now(), ChronoUnit.MINUTES);
		return minutesDurationOrder;
	}

	public Optional<StatusOrder> getStatusOrder() {
		return Optional.ofNullable(statusOrder);
	}
	
	public String getStatusOrderString() {
		return getStatusOrder().map( order -> order.toString()).orElse("");
	}

}
