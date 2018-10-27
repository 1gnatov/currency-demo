package ru.ignatov.currency.server.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

public class PaymentUtils {

	private static final long START_OF_LAST_YEAR = LocalDateTime.of(Year.now().getValue() - 1, Month.JANUARY, 1, 0, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond();
	private static final long START_OF_CURRENT_YEAR = LocalDateTime.of(Year.now().getValue(), Month.JANUARY, 1, 0, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond();
	private static final double MIN_AMOUNT = 10_000d;
	private static final double MAX_AMOUNT = 100_000d;

	private PaymentUtils() {
		// util class
	}

	public static LocalDateTime generateDateTimeInLastYear() {
		long randomDateInEpochSeconds = ThreadLocalRandom.current().nextLong(START_OF_LAST_YEAR, START_OF_CURRENT_YEAR);
		return Instant.ofEpochSecond(randomDateInEpochSeconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static double roundTwoNumberAfterPoint(double number) {
		return Math.round(number * 100d) / 100d; // можем позволить отбросить хвост т.к. вообще генерим эти значения
	}

	public static double generateAmount() {
		return roundTwoNumberAfterPoint(ThreadLocalRandom.current().nextDouble(MIN_AMOUNT, MAX_AMOUNT));
	}
}
