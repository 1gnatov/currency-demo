package ru.ignatov.currency.client.model;

import lombok.Data;
import ru.ignatov.currency.client.PaymentUtils;

import java.time.LocalDateTime;

@Data
public class Payment {

	private String officeName;
	private LocalDateTime dateTime;
	private double amount;

	public Payment(String officeName) {
		this.officeName = officeName;
		this.dateTime = PaymentUtils.generateDateTimeInLastYear();
		this.amount = PaymentUtils.generateAmount();
	}
}
