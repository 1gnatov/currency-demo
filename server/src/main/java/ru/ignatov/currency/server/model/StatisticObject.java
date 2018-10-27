package ru.ignatov.currency.server.model;

import java.math.BigDecimal;

public class StatisticObject {

	private int numberOfPayments = 1;
	private BigDecimal totalAmount;
	private BigDecimal totalComission;

	public StatisticObject(double initAmount, double initComission) {
		this.totalAmount = new BigDecimal(initAmount);
		this.totalComission = new BigDecimal(initComission);
	}

	public void addStatistic(double amount, double comission) {
		synchronized (this) {
			totalAmount = totalAmount.add(BigDecimal.valueOf(amount));
			totalComission = totalComission.add(BigDecimal.valueOf(comission));
			numberOfPayments++;
		}
	}

	public int getNumberOfPayments() {
		return numberOfPayments;
	}

	public double getTotalAmount() {
		return totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double getTotalComission() {
		return totalComission.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
