package ru.ignatov.currency.client.component;

import org.springframework.stereotype.Component;
import ru.ignatov.currency.client.model.Payment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Component
public class PaymentServiceImpl implements PaymentService {


	@Override
	public Stream<Payment> generatePayment(String officesFileName, Long numberOfPayments) throws IOException {
		List<String> officesNames = Files.lines(Paths.get(officesFileName)).collect(Collectors.toList());
		return LongStream.range(0L, numberOfPayments)
				.mapToObj(e -> new Payment(officesNames.get(ThreadLocalRandom.current().nextInt(officesNames.size()))));

	}
}
