package ru.ignatov.currency.server.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StatResponse {

	private List<OfficeStatResponse> officeStatResponseList = new ArrayList<>();
	private List<DateStatResponse> dateStatResponseList = new ArrayList<>();

}
