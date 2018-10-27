package ru.ignatov.currency.server.model;

import lombok.Data;

@Data
public class PaymentResponse {
	private String id;
	private double comission;

}
