package ru.ignatov.currency.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class OfficeStatResponse {
	private String office;
	private int numberOfPayments;
	private double totalAmount;
	private double totalComission;
}
