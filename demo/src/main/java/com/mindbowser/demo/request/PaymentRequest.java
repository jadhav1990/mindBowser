package com.mindbowser.demo.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PaymentRequest {

	private double amount;

	private long cardNumber;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/yy")
	private String expiryDate;

	private int cvv;

}
