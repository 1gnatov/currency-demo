package ru.ignatov.currency.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.ignatov.currency.client.component.ClientComponents;
import ru.ignatov.currency.client.component.PaymentService;
import ru.ignatov.currency.client.model.PaymentResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = {ClientComponents.class})
public class ClientApplication implements CommandLineRunner {

	private PaymentService paymentService;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length != 4) {
			log.warn("Invalid number of args: {}", args.length);
			return;
		}
		log.info("Do some client stuff");
		RestTemplate template = new RestTemplate();
		paymentService.generatePayment(args[0], Long.valueOf(args[1])).forEach(p -> {
			try {
				ResponseEntity<PaymentResponse> response = template.postForEntity(args[2], p, PaymentResponse.class);
				if (response.getStatusCode().is2xxSuccessful()) {
					PaymentResponse body = response.getBody();
					if (body != null && body.getId() != null) {
						String content = String.join(",", body.getId(), getPrettyString(p.getAmount()), p.getDateTime().toString(), p.getOfficeName(), getPrettyString(body.getComission()));
						content += "\r\n";
						Files.write(
								Paths.get(args[3]),
								content.getBytes(),
								StandardOpenOption.APPEND);
					}
				}
			} catch (IOException e) {
				log.error(e.getLocalizedMessage(), e);
			}
		});
		log.info("Done");

	}

	private static String getPrettyString(double number) {
		return String.format("%.2f", number);
	}

	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
}
