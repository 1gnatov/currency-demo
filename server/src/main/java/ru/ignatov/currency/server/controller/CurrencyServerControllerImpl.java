package ru.ignatov.currency.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ignatov.currency.server.component.StatisticComponent;
import ru.ignatov.currency.server.model.StatResponse;
import ru.ignatov.currency.server.utils.PaymentUtils;
import ru.ignatov.currency.server.model.PaymentRequest;
import ru.ignatov.currency.server.model.PaymentResponse;

import java.util.UUID;

@Slf4j
@Controller
public class CurrencyServerControllerImpl implements CurrencyServerController {

	private StatisticComponent statisticComponent;

	@PostMapping(path = "/pay")
	public @ResponseBody PaymentResponse acceptPayment(@RequestBody PaymentRequest paymentRequest) {
		log.info("payment request {}", paymentRequest);
		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse.setId(UUID.randomUUID().toString());
		double comission = paymentRequest.getAmount() * 0.15d;
		paymentResponse.setComission(PaymentUtils.roundTwoNumberAfterPoint(comission));
		statisticComponent.addStatistic(paymentRequest.getAmount(), paymentRequest.getDateTime(), paymentRequest.getOfficeName(), comission);
		return paymentResponse;
	}

	@GetMapping(path = "/stat")
	public @ResponseBody StatResponse getStatistic() {
		return statisticComponent.collectStatistic();

	}

	@Autowired
	public void setStatisticComponent(StatisticComponent statisticComponent) {
		this.statisticComponent = statisticComponent;
	}
}
