package com.atmosware.cleanarchwithcqrs.application.features.account.queries.getById;



import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.atmosware.cleanarchwithcqrs.application.features.account.queries.getAll.GetAccountTypesQuery;
import com.atmosware.cleanarchwithcqrs.domain.AccountType;
import com.atmosware.cleanarchwithcqrs.persistence.AccountTypeRepository;

public class GetByIdAccountTypesQueryHandler {

	private AccountTypeRepository accountTypeRepository;

	@Autowired
	public GetByIdAccountTypesQueryHandler(AccountTypeRepository accountTypeRepository) {
		
		this.accountTypeRepository = accountTypeRepository;
	}
	
	@QueryHandler
	public GetByIdAccountTypesDto getAllAccountTypes(GetByIdAccountTypesQuery getByIdAccountTypesQuery) {
	
		
		AccountType accountType = this.accountTypeRepository.findById(getByIdAccountTypesQuery.getId()).get();
		
		
			GetByIdAccountTypesDto getByIdAccountTypesDto = new GetByIdAccountTypesDto();
			BeanUtils.copyProperties(accountType, getByIdAccountTypesDto); // alanlar aynıysa çalışır isme göre map'lemez
			
			
		
		return getByIdAccountTypesDto;
		
	}
	
}
