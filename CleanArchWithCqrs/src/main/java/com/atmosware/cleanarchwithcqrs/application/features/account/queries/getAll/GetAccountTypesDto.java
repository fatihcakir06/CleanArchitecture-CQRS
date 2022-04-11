package com.atmosware.cleanarchwithcqrs.application.features.account.queries.getAll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAccountTypesDto {

	
	private String accountTypeId;
	
	private String accountName;
	
	private double price;

	private String description;
}
