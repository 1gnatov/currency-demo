package ru.ignatov.currency.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
public class DateStatResponse {
	private LocalDate date;
	private int numberOfPayments;
	private double totalAmount;
	private double totalComission;
}
