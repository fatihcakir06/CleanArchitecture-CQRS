package com.atmosware.cleanarchwithcqrs.ws.controllers;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atmosware.cleanarchwithcqrs.application.features.account.commands.create.CreateAccountTypeCommand;
import com.atmosware.cleanarchwithcqrs.application.features.account.queries.getAll.GetAccountTypesDto;
import com.atmosware.cleanarchwithcqrs.application.features.account.queries.getAll.GetAccountTypesQuery;
import com.atmosware.cleanarchwithcqrs.application.features.account.queries.getById.GetByIdAccountTypesDto;
import com.atmosware.cleanarchwithcqrs.application.features.account.queries.getById.GetByIdAccountTypesQuery;
import com.atmosware.cleanarchwithcqrs.ws.models.CreateAccountTypeModel;

@RestController
@RequestMapping("/accounttypes")
public class AccountTypesController {

	private CommandGateway commandGateway;
	private QueryGateway queryGateway;
	
	public AccountTypesController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}
	@PostMapping
	public void createAccountType(@RequestBody CreateAccountTypeModel createAccountTypeModel) {
		CreateAccountTypeCommand command = CreateAccountTypeCommand.builder()
				.price(createAccountTypeModel.getPrice())
				.accountName(createAccountTypeModel.getAccountName())
				.description(createAccountTypeModel.getDescription())
				.build();
		command.setAccountTypeId(UUID.randomUUID().toString());
		this.commandGateway.sendAndWait(command);
	}
	@GetMapping("getAll")
	public List<GetAccountTypesDto> getAll(){
		
	return	this.queryGateway.query(new GetAccountTypesQuery(), // kim bu sınıfı parametre istiyorsa çalıştır
				ResponseTypes.multipleInstancesOf(GetAccountTypesDto.class)).join();
	}
	
	@GetMapping("getById")
	public GetByIdAccountTypesDto getById(@RequestBody GetByIdAccountTypesQuery getByIdAccountTypesQuery){
		return this.queryGateway.query(getByIdAccountTypesQuery, ResponseTypes.instanceOf(GetByIdAccountTypesDto.class)).join();
		
	}
	
}
