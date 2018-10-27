package ru.ignatov.currency.server.component;

import org.springframework.stereotype.Component;
import ru.ignatov.currency.server.model.DateStatResponse;
import ru.ignatov.currency.server.model.OfficeStatResponse;
import ru.ignatov.currency.server.model.StatResponse;
import ru.ignatov.currency.server.model.StatisticObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StatisticComponentImpl implements StatisticComponent {

	private ConcurrentHashMap<String, StatisticObject> officeStatisticMap = new ConcurrentHashMap<>();
	private ConcurrentHashMap<LocalDate, StatisticObject> dateStatisticMap = new ConcurrentHashMap<>();

	@Override
	public void addStatistic(double amount, LocalDateTime dateTime, String officeName, double comission) {
		synchronized (this) {    // обязаны синхронизироваться иначе при получении статистики в другом методе можем потерять данные на добавление
			StatisticObject officeStatistic = officeStatisticMap.getOrDefault(officeName, new StatisticObject(amount, comission));
			officeStatistic.addStatistic(amount, comission);
			officeStatisticMap.put(officeName, officeStatistic);
			LocalDate localDate = dateTime.toLocalDate();
			StatisticObject dateStatistic = dateStatisticMap.getOrDefault(localDate, new StatisticObject(amount, comission));
			dateStatistic.addStatistic(amount, comission);
			dateStatisticMap.put(localDate, dateStatistic);
		}
	}

	@Override
	public StatResponse collectStatistic() {
		synchronized (this) { // иначе затрем запросы которые будут приходить во время составления отчета
			StatResponse result = new StatResponse();
			officeStatisticMap.forEach((office, officeStatistic) -> result.getOfficeStatResponseList().add(
					new OfficeStatResponse(office, officeStatistic.getNumberOfPayments(), officeStatistic.getTotalAmount(), officeStatistic.getTotalComission())));
			officeStatisticMap = new ConcurrentHashMap<>();
			dateStatisticMap.forEach((date, dateStatistic) -> result.getDateStatResponseList().add(
					new DateStatResponse(date, dateStatistic.getNumberOfPayments(), dateStatistic.getTotalAmount(), dateStatistic.getTotalComission())));
			dateStatisticMap = new ConcurrentHashMap<>();
			return result;
		}
	}
}
