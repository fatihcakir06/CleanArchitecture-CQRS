package com.atmosware.cleanarchwithcqrs.application.features.account.commands.create;

import lombok.Data;

@Data
public class AccountTypeCreatedEvent {
	private String accountTypeId;
	
	private String accountName;
	
	private double price;

	private String description;
}
