package ru.ignatov.currency.client.component;

import ru.ignatov.currency.client.model.Payment;

import java.io.IOException;
import java.util.stream.Stream;

public interface PaymentService {
	Stream<Payment> generatePayment(String officesFileName, Long numberOfPayments) throws IOException;
}
