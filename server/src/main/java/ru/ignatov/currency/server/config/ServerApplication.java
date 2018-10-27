package ru.ignatov.currency.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import ru.ignatov.currency.server.component.StatisticComponent;
import ru.ignatov.currency.server.controller.CurrencyServerController;

import java.util.HashMap;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = {CurrencyServerController.class, StatisticComponent.class})
public class ServerApplication {

	public static void main(String[] args) {
		if (args.length != 1) {
			log.warn("No port is setted by arguments");
			return;
		}
		HashMap<String, Object> props = new HashMap<>();
		props.put("server.port", Integer.valueOf(args[0]));
		new SpringApplicationBuilder()
				.sources(ServerApplication.class)
				.properties(props)
				.run(args);
	}
}
