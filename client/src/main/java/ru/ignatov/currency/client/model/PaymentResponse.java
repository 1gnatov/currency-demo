package ru.ignatov.currency.client.model;

import lombok.Data;

@Data
public class PaymentResponse {
	private String id;
	private double comission;
}