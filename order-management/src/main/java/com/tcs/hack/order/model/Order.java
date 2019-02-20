
package com.tcs.hack.order.model;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;

	private long customerId;
	private String paymentChannel;
	private boolean isCod;
	private Date orderCreatedOn;
	private double totalAmount;
	private String shippingAddress;

	@OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
	private Set<OrderLineItem> orderLI;

	public Order(Long customerId, String paymentChannel, boolean isCod, Date orderCreatedOn, double totalAmount,
			String shippingAddress, OrderLineItem... orderLineIt) {

		this.customerId = customerId;
		this.paymentChannel = paymentChannel;
		this.isCod = isCod;
		this.orderCreatedOn = orderCreatedOn;
		this.totalAmount = totalAmount;
		this.shippingAddress = shippingAddress;

		this.orderLI = Stream.of(orderLineIt).collect(Collectors.toSet());
		this.orderLI.forEach(x -> x.setOrderId(this.orderId));
	}
	public Order() {

	}

	public Set<OrderLineItem> getOrderLI() {
		return orderLI;
	}
	public void setOrderLI(Set<OrderLineItem> orderLI) {
		this.orderLI = orderLI;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getPaymentChannel() {
		return paymentChannel;
	}

	public void setPaymentChannel(String paymentChannel) {
		this.paymentChannel = paymentChannel;
	}

	public boolean isCod() {
		return isCod;
	}

	public void setCod(boolean isCod) {
		this.isCod = isCod;
	}

	public Date getOrderCreatedOn() {
		return orderCreatedOn;
	}

	public void setOrderCreatedOn(Date orderCreatedOn) {
		this.orderCreatedOn = orderCreatedOn;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

}
