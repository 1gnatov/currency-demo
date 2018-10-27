package ru.ignatov.currency.server.utils;

public class ServerPaymentUtils {

	private ServerPaymentUtils() {
		// util class
	}

	public static double roundTwoNumberAfterPoint(double number) {
		return Math.round(number * 100d) / 100d;
	}

}
