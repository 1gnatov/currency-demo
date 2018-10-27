package ru.ignatov.currency.server.component;

import ru.ignatov.currency.server.model.StatResponse;

import java.time.LocalDateTime;

public interface StatisticComponent {

	void addStatistic(double amount, LocalDateTime dateTime, String officeName, double comission);

	StatResponse collectStatistic(); // поидее надо объект != респонс объекту, для экономии времени
}
