package ru.ignatov.currency.server.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentRequest {
	private String officeName;
	private LocalDateTime dateTime;
	private double amount;
}
