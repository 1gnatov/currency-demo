package ru.ignatov.currency.server.model;

import lombok.Data;

import java.util.List;

@Data
public class StatResponse {

	private List<OfficeStatResponse> officeStatResponseList;
	private List<DateStatResponse> dateStatResponseList;

}
